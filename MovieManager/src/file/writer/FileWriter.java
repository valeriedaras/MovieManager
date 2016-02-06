package file.writer;

import model.File;
import model.Movie;

public class FileWriter {

	
	public FileWriter() {
		
	}
	
	public void saveMovie(Movie movie) {
		
	}
	
	private File movieToFile(Movie movie) {
		File f = new File() ;
		f.setName(movie.getTitle()+"."+movie.getYear());
		f.setExtension(movie.getFormat().getName());
		return f;
	}
	
}
