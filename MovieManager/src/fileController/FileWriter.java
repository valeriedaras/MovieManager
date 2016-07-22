package fileController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

import model.MovieFile;
import utils.Log;

public class FileWriter {

	private final Log logger = new Log("FileController", verbose);
	private static boolean verbose = true ;

	protected void createFile(String url){
		File f = new File(url);
		if(!f.exists()){
			f.mkdirs();
		}
	}

	protected void deleteFile(String url) throws FileNotFoundException{
		File file = new File (url);
		if(file.exists() || Files.isSymbolicLink(new File(url).toPath())){
			file.delete();
		}
	}


	protected void createFile(MovieFile j){
		File f = new File (j.toString());
		if(!f.exists()){
			f.mkdirs();
		}
	}


	protected void renameFile (String newUrl, String oldUrl) throws FileNotFoundException{
		Runtime R = Runtime.getRuntime();
		if(!newUrl.equals(oldUrl)) {
			try {
				R.exec("mv "+oldUrl+" "+newUrl);
			} catch (IOException e) {
				logger.logSevere("Renaming failed: {0}",e);
			}
		}
	}

	protected void fileWriter (String content, String path){
		File f = new File(path);	
		OutputStreamWriter writer=null;
		try {
			writer = new OutputStreamWriter (new FileOutputStream(f));
			writer.append(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (writer != null){
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		FileWriter writer = new FileWriter();
		try {
			String oldFile = "/Users/valeriedaras/Desktop/TOTO.avi" ;
			String newFile = "/Users/valeriedaras/Desktop/TOTO2.avi" ;
			writer.createFile(oldFile);
			writer.renameFile(newFile, oldFile);
			writer.deleteFile(oldFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}


}
