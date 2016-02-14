package fileController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;

import allocine.model.Writer;
import model.MovieFile;
import utils.Log;

public class FileWriter {

	private final Log logger = new Log("FileController", verbose);
	private static boolean verbose = true ;

	protected void createFile(String url){
		File f = new File(url);
		f.mkdirs();		
	}

	protected void deleteFile(String url) throws FileNotFoundException{

		File file = new File (url);
		file.delete();		
	}


	protected void createFile(MovieFile j){
		File f = new File (j.toString());
		f.mkdirs();

	}


	protected void renameFile (String newUrl, String oldUrl)throws FileNotFoundException{

		Runtime R = Runtime.getRuntime();

		try {
			R.exec("mv "+oldUrl+" "+newUrl);
		} catch (IOException e) {
			logger.logSevere("Renaming failed: {0}",e);

		}
	}

	protected void fileWriter (String content, String path){

		File f = new File(path);	
		OutputStreamWriter writer=null;
		try {
			writer = new OutputStreamWriter (new FileOutputStream(f));
			writer.append(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (writer != null){
					writer.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}




	}




}
