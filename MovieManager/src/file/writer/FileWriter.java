package file.writer;

import model.MovieFile;
import model.Movie;

public class FileWriter {

	
	public FileWriter() {
		
	}
	
	public void saveMovie(Movie movie) {
		
	}
	
	private MovieFile movieToFile(Movie movie) {
		MovieFile f = new MovieFile() ;
		f.setName(movie.getTitle()+"."+movie.getYear());
		f.setExtension(movie.getFormat().getName());
		return f;
	}
	
}
