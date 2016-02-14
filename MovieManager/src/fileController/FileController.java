package fileController;
import java.io.IOException;
import model.MovieFile;


public class FileController {
	
	private final FileReader reader ;
	private final FileWriter writer;
	public static final String moviePath="src/";
	public static final String movieInfoPath="src/movieInfoPath/";
	
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
