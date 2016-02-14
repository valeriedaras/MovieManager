package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import fileController.FileController;
import model.InvalidMovieFileException;
import model.Movie;
import model.MovieFile;
import utils.Log;
import allocine.AllocineException;
import allocine.AllocineManager;
import allocine.NoMovieFoundException;

public class MovieManager {
	
	private AllocineManager allocineManager ;
	
	private FileController fileController ;
	
	/**
	 * Logger
	 */
	private static final Log logger = new Log("MovieManager", true);
	
	public MovieManager() {
		this.allocineManager = new AllocineManager() ;
		this.fileController = new FileController() ;
	}
	
	public Movie searchMovie(MovieFile file) {
		try {
			return allocineManager.searchMovies(file);
		} catch (AllocineException | NoMovieFoundException e) {
			logger.logInfo("Movie \"{0}\" not found. Has to be moved into another folder.", file);
		}
		return null;
	}
	
	public void performFileCreated(String path) {
		logger.logInfo("Perform File Created: {0}",path);
		MovieFile mFile = this.fileController.performRetrieveInfoFile(path);
		Movie movie = searchMovie(mFile);
		if(movie != null) {
			System.out.println("*************************");
			System.out.println(movie);
		}
		try {
			mFile.update(movie);
			fileController.performFileWrite(mFile);
			// + fileController.updateSymbolicLinks(mFile);
		} catch (InvalidMovieFileException e) {
			logger.logSevere("Invalid Movie File Exception: the movie has to been moved into another folder.");
		}
	}
	
	public void performFileDeleted(String path) {
		logger.logInfo("Perform File Deleted: {0}",path);
	}
	
	public FileController getFileController() {
		return fileController;
	}
	
	public static void main(String[] args) {
		MovieManager movieManager = new MovieManager() ;
		FileInputStream fstream;
		MovieFile file = null;
		try {
			fstream = new FileInputStream("src/corpus.txt");
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
			    for(String line; (line = br.readLine()) != null; ) {
					//file = movieManager.getFileController().performRetrieveInfoFile(line) ;
					//Movie movie = movieManager.searchMovie(file);
					/*if(movie != null) {
						System.out.println("*************************");
						System.out.println(movie);
					}
*/			    
			    	movieManager.performFileCreated(line);
			    }
			} catch (IOException e) {
				
			}
		} catch (FileNotFoundException e) {
			
		}
		
	}

}
