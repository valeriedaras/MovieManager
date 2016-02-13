package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import file.reader.FileReader;
import fileController.FileController;
import model.Movie;
import model.MovieFile;
import utils.Log;
import allocine.AllocineException;
import allocine.AllocineManager;
import allocine.NoMovieFoundException;

public class MovieManager {
	
	private AllocineManager api ;
	
	private FileController fileController ;
	
	/**
	 * Logger
	 */
	private static final Log logger = new Log("MovieManager", true);
	
	public MovieManager() {
		this.api = new AllocineManager() ;
		this.fileController = new FileController() ;
	}
	
	public Movie searchMovie(MovieFile file) {
		try {
			return api.searchMovies(file);
		} catch (AllocineException | NoMovieFoundException e) {
			logger.logInfo("Movie {0} not found. Has to be moved into another folder.", file);
		}
		return null;
	}
	
	public static void main(String[] args) {
		MovieManager movieManager = new MovieManager() ;
		FileReader f = new FileReader() ;
		FileInputStream fstream;
		MovieFile file = null;
		try {
			fstream = new FileInputStream("src/corpus.txt");
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
			    for(String line; (line = br.readLine()) != null; ) {
					file = f.retrieveInfosFile(line) ;
					Movie movie = movieManager.searchMovie(file);
					if(movie != null) {
						System.out.println("*************************");
						System.out.println(movie);
					}
			    }
			} catch (IOException e) {
				
			}
		} catch (FileNotFoundException e) {
			
		}
		
	}

}
