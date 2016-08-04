package fileController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.scene.Parent;
import utils.Log;
import model.MovieFile;


public class FileController {
	
	/**
	 * Path to all movies
	 */
	public static final String moviePath="/Users/valeriedaras/Desktop/Movies/";
	
	/**
	 * Path to unknown movies
	 */
	public static final String unknownMoviePath="/Users/valeriedaras/Desktop/Unknown_Movies/";
	
	/**
	 * Path to the information files of all movies
	 */
	public static final String movieInfoPath="/Users/valeriedaras/Desktop/Movie_Info/";
	
	/**
	 * Path to the movie genres
	 */
	public static final String movieGenrePath="/Users/valeriedaras/Desktop/Genres/";
	
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
	private static final Log logger = new Log("FileController", true); 
	
	/**
	 * Constructor
	 */
	public FileController(){
		reader = new FileReader();
		writer = new FileWriter();
		init();
	}
	
	/**
	 * Method to initialize paths
	 */
	protected void init (){
		writer.createFile(moviePath);
		writer.createFile(unknownMoviePath);
		writer.createFile(movieInfoPath);
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
			logger.logInfo("Write to file: {0}", movieInfoPath+f.getCompleteMovieName()+".txt");
			writer.fileWriter(f.toJSON(),movieInfoPath+f.getCompleteMovieName()+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to rename a file
	 * @param f
	 */
	public void performUpdateMovie(MovieFile f){
		//Renommage des films avec le nom title+SEPARATOR+year+extension(MovieFile)
		try {
			logger.logInfo("Move : mv {0} >> {1}", new Object[]{f.getFileName(), f.getCompleteMovieNameWithAbsolutePath()});
			writer.renameFile(f.getCompleteMovieNameWithAbsolutePath(), f.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
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
			System.out.println("Create Genre Dir: " +str);
			writer.createFile(movieGenrePath+str);	
			try {
				logger.logInfo("Link : ln -s {0} >> {1}", new Object[]{f.getFileNameWithAbsolutePath(), movieGenrePath+f.getCompleteFileName()});
				R.exec("ln -s "+f.getFileNameWithAbsolutePath()+" "+movieGenrePath+str+"/"+f.getCompleteMovieNameWithExt());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to move a movie into the unknown movie folder
	 * @param f
	 */
	public void performUnknownMovie(MovieFile f){
		try {
			File file = new File(f.getFileNameWithAbsolutePath()) ;
			File parent = file.getParentFile();
			String path = "";
			while(!(parent.getAbsolutePath()+"/").equals(moviePath)) {
				path = parent.getName();
				parent = parent.getParentFile() ;
			} 
			System.out.println("Found path: "+ path);
			
			if(path.isEmpty()) {
				System.out.println("mv " + f.getFileNameWithAbsolutePath() + " --> " +unknownMoviePath + f.getCompleteFileName());
				writer.renameFile(unknownMoviePath + f.getCompleteFileName(), f.getFileNameWithAbsolutePath());
			}
			else {
				System.out.println("mv " + moviePath + path + " --> " + unknownMoviePath + path);
				writer.renameFile(unknownMoviePath + path, moviePath + path);
			}
		} catch (FileNotFoundException e) {
			logger.logSevere("File not found.");
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
				logger.logSevere("File not found.");
			}
		}
			
		
	}
}
