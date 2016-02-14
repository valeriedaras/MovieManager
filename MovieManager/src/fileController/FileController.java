package fileController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.MovieFile;
import utils.Log;


public class FileController {
	
	private final FileReader reader ;
	private final FileWriter writer;
	public static final String moviePath="src/";
	public static final String movieInfoPath="src/";
	
	public FileController(){
		reader = new FileReader();
		writer = new FileWriter();
	}
			
	public MovieFile performRetrieveInfoFile(String path){
		return this.reader.retrieveInfosFile(path);
	}
	
	public void performFileWrite (MovieFile f){
		
		try {
			writer.fileWriter(f.toJSON(),movieInfoPath+ f.getTxtPath()+".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
