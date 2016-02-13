package fileController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.JSONFile;
import model.MovieFile;
import utils.Log;


public class FileController {
	
	private static boolean verbose = true ;
	
	private final Log logger = new Log("FileController", verbose);
		
	private void createFile(String url){
		File f = new File(url);
		f.mkdirs();		
	}

	private void deleteFile(String url) throws FileNotFoundException{
		
		File file = new File (url);
		file.delete();		
	}
	
	public void createFile(MovieFile j) throws IOException{
		
		File f = new File(j.toString());
		f.mkdirs();
		
		FileOutputStream ostream = new FileOutputStream(f);
		ObjectOutputStream o = new ObjectOutputStream(ostream);
		o.writeObject(j);
		
	}	
	
	public void renameFile (String newUrl, String oldUrl)throws FileNotFoundException{
		
		Runtime R = Runtime.getRuntime();
		
		try {
			R.exec("mv "+oldUrl+""+newUrl);
		} catch (IOException e) {
			logger.logSevere("Renaming failed: {0}",e);
			
		}
		
	}
}
