package fileController;
import java.io.IOException;
import model.MovieFile;


/*
 * TO DO:
 * - Create "init()" function called from the constructor : creating all folders
 * - Create "performUpdateMovie(MovieFile)" : renaming the movie + creating all genres 
 * 			+ creating all symbolic links
 * - Create "performUnknownMovie(MovieFile)" : moving the movie into unknownMoviePath  
 */

public class FileController {
	
	private final FileReader reader ;
	private final FileWriter writer;
	public static final String moviePath="src/";
	public static final String unknownMoviePath="src/unknownMovies";
	public static final String movieInfoPath="src/movieInfo/";
	
	
	public FileController(){
		reader = new FileReader();
		writer = new FileWriter();
		// To remove later
		writer.createFile(movieInfoPath);
	}
			
	public MovieFile performRetrieveInfoFile(String path){
		return this.reader.retrieveInfosFile(path);
	}
	
	public void performFileWrite (MovieFile f){
		try {
			writer.fileWriter(f.toJSON(),movieInfoPath+f.getTxtPath()+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		new FileController();
	}
}
