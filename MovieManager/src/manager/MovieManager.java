package manager;

import directory.Watcher;
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
	
	private Watcher watcher ;
	
	/**
	 * Logger
	 */
	private static final Log logger = new Log("MovieManager", true);
	
	public MovieManager() {
		this.allocineManager = new AllocineManager() ;
		this.fileController = new FileController() ;
		this.watcher = new Watcher(this, "/Users/valeriedaras/Desktop/");
		this.watcher.start();
	}
	
	public Movie searchMovie(MovieFile file) {
		try {
			return allocineManager.searchMovies(file);
		} catch (AllocineException | NoMovieFoundException e) {
			logger.logInfo("No Movie Found Exception: Movie \"{0}\" not found. Has to be moved into another folder.", file);
			// fileController.move() 
		}
		return null;
	}
	
	public void performFileCreated(String path, String name) {
		System.out.println("*************************");
		logger.logInfo("Perform File Created: {0}",name);
		MovieFile mFile = this.fileController.performRetrieveInfoFile(name);
		mFile.setPath(path);
		Movie movie = searchMovie(mFile);
		if(movie != null) {
			System.out.println(movie);
		}
		try {
			mFile.update(movie);
			fileController.performFileWrite(mFile);
			// + fileController.updateMovieFile(mFile)
			watcher.addToIndex(mFile);
		} catch (InvalidMovieFileException e) {
			logger.logSevere("Invalid Movie File Exception: Movie \"{0}\" has to been moved into another folder.", mFile);
			// fileController.move() 
		}
	}
	
	public void performFileDeleted(String path) {
		logger.logInfo("Perform File Deleted: {0}",path);
		// Watcher: Remove from index 
	}
	
	public FileController getFileController() {
		return fileController;
	}
	
	public static void main(String[] args) {
		new MovieManager() ;
	}

}
