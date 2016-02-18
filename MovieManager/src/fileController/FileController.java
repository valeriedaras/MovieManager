package fileController;
import java.io.FileNotFoundException;
import java.io.IOException;

import utils.Log;
import model.MovieFile;


public class FileController {
	
	private final FileReader reader ;
	private final FileWriter writer;
	public static final String moviePath="/Users/valeriedaras/Desktop/Movies/";
	public static final String unknownMoviePath="/Users/valeriedaras/Desktop/Unknown_Movies/";
	public static final String movieInfoPath="/Users/valeriedaras/Desktop/Movie_Info/";
	public static final String movieGenrePath="/Users/valeriedaras/Desktop/Genres/";
	
	private static final Log logger = new Log("FileController", true); 
	
	public FileController(){
		reader = new FileReader();
		writer = new FileWriter();
		init();
	}
	
	protected void init (){
		writer.createFile(moviePath);
		writer.createFile(unknownMoviePath);
		writer.createFile(movieInfoPath);
	}
			
	public MovieFile performRetrieveInfoFile(String path){
		return this.reader.retrieveInfosFile(path);
	}
	
	public void performFileWrite (MovieFile f){
		try {
			logger.logInfo("Write to file: {0}", movieInfoPath+f.getCompleteMovieName()+".txt");
			writer.fileWriter(f.toJSON(),movieInfoPath+f.getCompleteMovieName()+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void performUpdateMovie(MovieFile f){
		//Renommage des films avec le nom title+SEPARATOR+year+extension(MovieFile)
		try {
			logger.logInfo("Move : mv {0} >> {1}", new Object[]{f.getFileName(), f.getCompleteMovieNameWithAbsolutePath()});
			writer.renameFile(f.getCompleteMovieNameWithAbsolutePath(), f.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
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
	
	//Deplace un film non vers le unknownMoviePath
	public void performUnknownMovie(MovieFile f){
		
		try {
			writer.renameFile(unknownMoviePath+"/"+f.getCompleteFileName(), f.getFileNameWithAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
