package fileController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.MovieFile;

public class FileReader {

	private List<String> blacklist ;
	
	private static final String SEPARATOR = " " ;
	
	private static final String BLACKLIST_FILE = "src/blacklist.txt" ;
	
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
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public static void main(String[] args) {
		FileReader f = new FileReader() ;
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
		
	}
}
