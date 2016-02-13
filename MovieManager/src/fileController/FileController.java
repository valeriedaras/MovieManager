package fileController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.MovieFile;
import utils.Log;


public class FileController {
	
	private final FileReader reader ;
	
	private static boolean verbose = true ;
	
	private final Log logger = new Log("FileController", verbose);
	
	public FileController(){
		reader = new FileReader();
	}
	
	private void createFile(String url){
		File f = new File(url);
		f.mkdirs();		
	}

	private void deleteFile(String url) throws FileNotFoundException{
		
		File file = new File (url);
		file.delete();		
	}
	
	
	public void createFile(MovieFile j){
		File f = new File (j.toString());
		f.mkdirs();
	
	}
	
	
	public void renameFile (String newUrl, String oldUrl)throws FileNotFoundException{
		
		Runtime R = Runtime.getRuntime();
		
		try {
			R.exec("mv "+oldUrl+""+newUrl);
		} catch (IOException e) {
			logger.logSevere("Renaming failed: {0}",e);
			
		}
	}
	
	public MovieFile performRetrieveInfoFile(String path){
		return this.reader.retrieveInfosFile(path);
	}
}
