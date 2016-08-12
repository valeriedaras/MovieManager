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
		logger.info("Initialized.");
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
		logger.info("Written into: {}", movieInfoPath+f.getCompleteMovieName()+".txt");
	}
	
	/**
	 * Method to rename a file
	 * @param f
	 */
	public void performUpdateMovie(MovieFile f){
		//Renommage des films avec le nom title+SEPARATOR+year+extension(MovieFile)
		try {
			writer.renameFile(f.getCompleteMovieNameWithAbsolutePath(), f.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		logger.info("File moved from {} >> {}", new Object[]{f.getFileName(), f.getCompleteMovieNameWithAbsolutePath()});
	}
	
	/**
	 * Method to create all symbolic links of a movie
	 * @param f
	 */
	public void performSymbolicLinks(MovieFile f){
		//Creation des dossiers GENRE et cree les liens qui vont avec
		Runtime R = Runtime.getRuntime();
		for (String str: f.getSymbolicLinks()){
			//System.out.println("Create Genre Dir: " +str);
			writer.createDir(movieGenrePath+str);	
			try {
				R.exec("ln -s "+f.getFileNameWithAbsolutePath()+" "+movieGenrePath+str+"/"+f.getCompleteMovieNameWithExt());
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("Link created from {} >> {}", new Object[]{f.getFileNameWithAbsolutePath(), movieGenrePath+f.getCompleteFileName()});
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
			logger.debug("Found path of file: "+ path);
			
			if(path.isEmpty()) {
				writer.renameFile(unknownMoviePath + f.getCompleteFileName(), f.getFileNameWithAbsolutePath());
				logger.info("File move from " + f.getFileNameWithAbsolutePath() + " >> " +unknownMoviePath + f.getCompleteFileName());
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
