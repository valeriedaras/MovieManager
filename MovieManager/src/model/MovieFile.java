package model;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MovieFile {
	
	private Movie movie ;
	
	private String title ;
	
	private String year ;
	
	private String extension ;
	
	private String path ;
	
	private List<String> symbolicLinks ;
	
	private static final String SEPARATOR = "_" ;
	
	public MovieFile() {
		this.symbolicLinks = new ArrayList<String>() ;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		if (year != null) {
			if (year.length() == 4) {
				this.year = year ;
			}
		}
	}
	
	public String getTitle() {
		return title;
	}

	private void setTitle(Movie m) throws InvalidMovieFileException{
		if (m != null) {
			if (m.getOriginalTitle() != null) {
				this.title = setSeparator(cleanString(m.getOriginalTitle()));
			}
			else {
				throw new InvalidMovieFileException();
			}
		}
	}
	
	public void setTitle(String m) {
		this.title = m ;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getPath(){
		return path;
	}
	
	public String getNameWithAbsolutPath(){
		return path+this.toString();
	}
	
	public void setPath(String path){
		if(Paths.get(path).toFile().isDirectory()) {
			this.path = path;
		}
		else {
			this.path = Paths.get(path).getParent().toString()+"/";
		}
	}
	
	private void addSymbolicLink(Genre g) {
		if (!this.symbolicLinks.contains(g.getName())) {
			this.symbolicLinks.add(g.getName());
		}
	}
	
	public void updateMovie(Movie movie) throws InvalidMovieFileException {
		if(movie == null) {
			throw new InvalidMovieFileException();
		}
		this.movie = movie;
		for(Genre g : movie.getGenres()) {
			this.addSymbolicLink(g);
		}
	}
	
	public void updateFile() throws InvalidMovieFileException {
		if (this.movie != null) {
			this.setTitle(this.movie);
			this.setYear(movie.getYear());
		}
	}
	
	public String toString() {
		if (this.year != null) {
			return title+SEPARATOR+year+extension;
		}
		else {
			return title+extension;
		}
	}
	
	public String getTxtPath() {
		return this.toString();
	}
	
	public String toJSON() throws IOException {
		if (movie == null) {
			return null;
		}
		return movie.toJSON();
	}
	
	private String cleanString(String s) {
		String str = s.replaceAll("[\\p{Punct}]+", "");
		return str;
	}
	
	private String setSeparator(String s) {
		String[] strTab = s.split(" ") ;
		String str = new String() ;
		for (int i=0; i<strTab.length-1; i++) {
			str+= strTab[i] + SEPARATOR ;
		}
		return str+strTab[strTab.length-1];
	}
	
	public List<String> getSymbolicLinks() {
		return this.symbolicLinks;
	}
}

