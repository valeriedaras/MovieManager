package file.reader;

import model.File;

public class FileReader {

	private String filePath ;
	
	public FileReader(String file) {
		this.filePath = file;
	}
	
	public File retrieveInfosFile() {
		if (filePath != null) {
			File file = new File() ;
			
			// Get path
			String[] path = filePath.split("/") ;
			String pathStr = new String() ;
			for (int i=0; i<path.length-1; i++) {
				pathStr += path[i]+"/" ;
			}
			file.setPath(pathStr);
			
			// Get potential title
			String [] name = path[path.length-1].split("[\\s\\p{Punct}]+") ;
			String nameStr = new String() ;
			for (int i=0; i<name.length-1; i++) {
				if (!isInteger(name[i])) {
					nameStr += name[i]+" " ;
				}
				else {
					break ;
				}
				
			}
			file.setName(nameStr);
			
			// Set Extension
			file.setExtension("."+name[name.length-1]);
			
			System.out.println("File:" + file);
			return file ;
		}
		return null ;
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
		FileReader f = new FileReader("Cinquante.Nuances.de.Grey.2015.FRENCH.BRRip.XviD-QCP.avi") ;
		f.retrieveInfosFile() ;
	}
}
