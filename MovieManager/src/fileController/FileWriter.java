package fileController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import model.MovieFile;

public class FileWriter {

	private static Logger logger = LogManager.getLogger("FileController");

	protected void createDir(String url){
		File f = new File(url);
		if(!f.exists()){
			f.mkdirs();
			logger.info("Created new directory: {}", url);
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
				logger.fatal("Renaming/moving file failed: {}. The system will be stopped.",e);
				System.exit(-1);
			}
		}
	}
	
	protected void renameFolder (String newUrl, String oldUrl) throws FileNotFoundException{
		Runtime R = Runtime.getRuntime();
		if(!newUrl.equals(oldUrl)) {
			try {
				R.exec("mv "+oldUrl+" "+newUrl);
			} catch (IOException e) {
				logger.fatal("Renaming/moving folder failed: {}. The system will be stopped.",e);
				System.exit(-1);
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
			logger.fatal("Writting into a file failed: {}. The system will be stopped.",e);
			System.exit(-1);
		}
		finally{
			try {
				if (writer != null){
					writer.close();
				}
			} catch (IOException e) {
				logger.fatal("Closing a file failed: {}. The system will be stopped.",e);
				System.exit(-1);
			}
		}
	}

}
