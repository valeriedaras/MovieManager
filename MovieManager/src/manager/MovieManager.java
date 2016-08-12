package manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import directory.Watcher;
import fileController.FileController;
import model.InvalidMovieFileException;
import model.Movie;
import model.MovieFile;
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
	private static Logger logger = LogManager.getLogger("MovieManager");
	
	public MovieManager(String rootpath) {
		this.allocineManager = new AllocineManager() ;
		this.fileController = new FileController(rootpath+"/Tous/", rootpath+"/Inconnus/", rootpath+"/.Informations/", rootpath+"/Genres/") ;
		this.watcher = new Watcher(this, rootpath+"/Tous/");
		this.watcher.start();
	}
	
	public Movie searchMovie(MovieFile file) {
		try {
			return allocineManager.searchMovies(file);
		} catch (AllocineException | NoMovieFoundException e) {
			logger.warn("No movie found: Movie \"{}\" will be moved into another folder.", file);
			fileController.performUnknownMovie(file);
		}
		return null;
	}
	
	public void performFileCreated(String path, String name) {
		logger.info("{}",name);
		MovieFile mFile = this.fileController.performRetrieveInfoFile(name);
		mFile.setAbsolutePath(path);
		mFile.setFileName(name);
		Movie movie = searchMovie(mFile);
		if(movie != null) {
			try {
				mFile.updateMovie(movie);
				fileController.performFileWrite(mFile);
				fileController.performUpdateMovie(mFile);
				mFile.updateFile();
				fileController.performSymbolicLinks(mFile);
				watcher.addToIndex(mFile);
			} catch (InvalidMovieFileException e) {
				logger.warn("Invalid movie file: Movie \"{}\" will be moved into another folder.", mFile);
				fileController.performUnknownMovie(mFile); 
			}
		}
	}
	
	public void performFileDeleted(String path) {
		logger.info("{}",path);
		watcher.removeFromIndex(path);
		fileController.performRemoveSymbolicLinks(path);
	}
	
	public FileController getFileController() {
		return fileController;
	}
	
	public static void main(String[] args) {
		new MovieManager(args[0]) ;
	}

}
