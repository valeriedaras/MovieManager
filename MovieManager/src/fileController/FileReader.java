package fileController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.MovieFile;

public class FileReader {

	private List<String> blacklist ;
	
	private static final String SEPARATOR = " " ;
	
	private static final String BLACKLIST_FILE = ".blacklist.txt" ;
	
	private static Logger logger = LogManager.getLogger("FileReader");
	
	/**
	 * Constructor
	 */
	protected FileReader() {
		this.blacklist = new ArrayList<String>() ;
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(BLACKLIST_FILE);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
			    for(String line; (line = br.readLine()) != null; ) {
			        this.blacklist.add(line);
			    }
			} catch (IOException e) {
				logger.error("Blacklist could not be read. The system will be stopped.");
				System.exit(-1);
			}
		} catch (FileNotFoundException e) {
			logger.fatal("Blacklist not found. The system will be stopped.");
			System.exit(-1);
		}
	}
	
	/**
	 * Method to get all information from a path
	 * @param path to file
	 * @return
	 */
	protected MovieFile retrieveInfosFile(String file) {

		if (file != null) {
			MovieFile mFile = new MovieFile() ;
			
			// Get path
			String[] path = file.split("/") ;
			
			// Get potential title
			String [] name = path[path.length-1].split("[\\s\\p{Punct}]+") ;
			String nameStr = new String() ;
			for (int i=0; i<name.length-1; i++) {
				if (blacklist.contains(name[i])) {
					if (nameStr.endsWith(" ") || nameStr.endsWith(SEPARATOR)) {
						nameStr=nameStr.substring(0, nameStr.length()-1);
					}
					break;
				}
				else if (!isYear(name[i])) {
					nameStr += name[i]+SEPARATOR ;
				}
				else {
					if (nameStr.isEmpty()) {
						nameStr += name[i]+SEPARATOR ;
					}
					else {
						mFile.setFileYear(name[i]) ;
					}				
				}
			}
			nameStr=nameStr.trim();
			mFile.setFileTitle(nameStr);
			
			// Set Extension
			mFile.setFileExtension("."+name[name.length-1]);
			
			return mFile ;
		}
		return null ;
	}
	
	private static boolean isYear(String s) {
	    if (isInteger(s)) {
	    	return s.length() == 4;
	    }
	    return false ;
	}
	
	private static boolean isInteger(String s) {
	    try {
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	/**
	 * Method to get the list of all symbolic links of a file
	 * All genres folders are explored to find the symbolic links
	 * @param path to input file
	 * @return
	 */
	public List<String> getSymbolicLinks(String absPath, String[] genres, String file) {
		List<String> links = new ArrayList<String>() ;
		for(String l : genres) {
			Path path = new File(absPath+"/"+l+"/"+file).toPath() ;
			if(Files.isSymbolicLink(path)) { 
				links.add(path.toString());
			}
		}
		return links;
	}
	
	/**
	 * Method to get the list of all sub-directories of a directory
	 * @param path
	 * @return
	 */
	public String[] getListSubdirectories(String path) {
		File file = new File(path);
		String[] subdirs = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});		
		return subdirs;
	}
	
	public static void main(String[] args) {
		FileReader f = new FileReader() ;
		
		System.out.println("Test n° 1") ;
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("src/corpus.txt");
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
			    for(String line; (line = br.readLine()) != null; ) {
			        System.out.println(line) ;
					f.retrieveInfosFile(line) ;
			    }
			    // line is not visible here.
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nTest n° 2") ;
		String path = "/Users/valeriedaras/Desktop/Genres" ;
		String [] subdirs = f.getListSubdirectories(path);
		for(String n : subdirs) {
			System.out.println("Dir: "+ n);
		}
		
		System.out.println("\nTest n° 3") ;
		List<String> links = (ArrayList<String>)f.getSymbolicLinks(path, subdirs, "Titanic_1953.avi");
		for(String n : links) {
			System.out.println("Dir: "+ n);
		}
	}
}
