package fileController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.MovieFile;


public class FileController {
	
	/**
	 * Path to all movies
	 */
	private final String moviePath;
	
	/**
	 * Path to unknown movies
	 */
	public final String unknownMoviePath;
	
	/**
	 * Path to the information files of all movies
	 */
	public final String movieInfoPath;
	
	/**
	 * Path to the movie genres
	 */
	public final String movieGenrePath;
	
	/**
	 * Field of the file reader
	 */
	private final FileReader reader ;
	
	/**
	 * Field of the file writer
	 */
	private final FileWriter writer;
	
	/**
	 * Field of the logger
	 */
	private static Logger logger = LogManager.getLogger("FileController");
	
	/**
	 * Constructor
	 */
	public FileController(String moviePath, String unknownMoviePath, String movieGenrePath, String movieInfoPath){
		this.moviePath = moviePath;
		this.unknownMoviePath = unknownMoviePath;
		this.movieGenrePath = movieGenrePath;
		this.movieInfoPath = movieInfoPath;
		reader = new FileReader();
		writer = new FileWriter();
		init();
	}
	
	/**
	 * Method to initialize paths
	 */
	protected void init (){
		writer.createDir(moviePath);
		writer.createDir(unknownMoviePath);
		writer.createDir(movieInfoPath);
	}
	
	/** 
	 * Method to get all information from a movie file
	 * @param path
	 * @return
	 */
	public MovieFile performRetrieveInfoFile(String path){
		return this.reader.retrieveInfosFile(path);
	}
	
	/**
	 * Method to write all information of a movie into a file
	 * @param f
	 */
	public void performFileWrite (MovieFile f){
		try {
			writer.fileWriter(f.toJSON(),movieInfoPath+f.getCompleteMovieName()+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("Written into: {}", movieInfoPath+f.getCompleteMovieName()+".txt");
	}
	
	/**
	 * Method to rename a file
	 * @param f
	 */
	public void performUpdateMovie(MovieFile f){
		File file = new File(f.getFileNameWithAbsolutePath()) ;
		
		// Renaming movie file
		try {
			writer.renameFile(moviePath + f.getCompleteMovieNameWithExt(), f.getFileName());
			logger.info("File renamed from {} >> {}", new Object[]{f.getFileName(), moviePath + f.getCompleteMovieNameWithExt()});
			f.setAbsolutePath(moviePath + f.getCompleteMovieNameWithExt());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			logger.fatal("Thread interruption while sleeping.");
		}
		
		// Removing useless folders
		File parent = file.getParentFile();
		while(!(parent.getAbsolutePath()+"/").equals(moviePath)) {

			if(!reader.hasDirMovieFiles(parent.getAbsolutePath())) {
				try {
					Runtime R = Runtime.getRuntime();
					R.exec("rm -R "+ parent.getAbsolutePath());
				} catch (IOException e) {
					logger.fatal("Folder could not be removed - {}", e);
				}
				logger.info("Folder {} has been removed.", parent.getAbsolutePath());
			}
			
			parent = parent.getParentFile() ;
		}
	}
	
	/**
	 * Method to create all symbolic links of a movie
	 * @param f
	 */
	public void performSymbolicLinks(MovieFile f){
		//Creation des dossiers GENRE et cree les liens qui vont avec
		Runtime R = Runtime.getRuntime();
		for (String str: f.getSymbolicLinks()){
			writer.createDir(movieGenrePath+str);
			try {
				R.exec("ln -s "+f.getFileNameWithAbsolutePath()+" "+movieGenrePath+str+"/"+f.getCompleteMovieNameWithExt());
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.debug("Link created from {} >> {}", new Object[]{f.getFileNameWithAbsolutePath(), movieGenrePath+f.getCompleteFileName()});
		}
	}
	
	/**
	 * Method to move a movie into the unknown movie folder
	 * @param f
	 */
	public void performUnknownMovie(MovieFile f){
		try {
			File file = new File(f.getFileName()) ;
			File parent = file.getParentFile();
			String path = "";
			while(!(parent.getAbsolutePath()+"/").equals(moviePath)) {
				path = parent.getName();
				parent = parent.getParentFile() ;
			} 
			logger.debug("Found path of file: "+ path);
			
			if(path.isEmpty()) {
				writer.renameFile(unknownMoviePath, f.getFileName());
				logger.info("File move from " + f.getFileName() + " >> " +unknownMoviePath);
			}
			else {
				writer.renameFile(unknownMoviePath + path, moviePath + path);
				logger.info("File move from " + moviePath + path + " >> " + unknownMoviePath + path);
			}
		} catch (FileNotFoundException e) {
			logger.fatal("File not found.");
		}
	}
	
	/**
	 * Method to remove all symbolic links of a movie
	 * @param Movie name in folder "moviePath"
	 */
	public void performRemoveSymbolicLinks(String path){
		// Get movie name without path
		String[] movie = path.split("/");
		// Get list of all genres
		String [] genres = reader.getListSubdirectories(movieGenrePath);
		// Get list of all symbolic links of the movie in genre folders
		List<String> links = reader.getSymbolicLinks(movieGenrePath,genres,movie[movie.length-1]);
		
		for(String str : links) {
			try {
				writer.deleteFile(str);
			} catch (FileNotFoundException e) {
				logger.fatal("File not found.");
			}
		}
	}
}
