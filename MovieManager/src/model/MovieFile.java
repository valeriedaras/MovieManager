package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieFile {
	
	private Movie movie ;
	
	private String title ;
	
	private String year ;
	
	private String extension ;
	
	private List<String> symbolicLinks ;
	
	private static final String PATH = ".all" ;
	
	private static final String SEPARATOR = "_" ;
	
	public MovieFile() {
		this.symbolicLinks = new ArrayList<String>() ;
	}
	
	
	public MovieFile(String title, String year, String extension) throws InvalidMovieFileException {
		this.symbolicLinks = new ArrayList<String>() ;
		this.setTitle(title);
		this.setYear(year);
		this.extension = extension ;
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
				if(m.getYear() != null) {
					this.title = setSeparator(m.getOriginalTitle())+SEPARATOR+m.getYear();
				}
				else {
					this.title = setSeparator(m.getOriginalTitle());
				}
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
	
	private void addSymbolicLink(Genre g) {
		if (!this.symbolicLinks.contains(g.getName())) {
			this.symbolicLinks.add(g.getName());
		}
	}
	
	public void update(Movie movie) throws InvalidMovieFileException {
		this.movie = movie;
		this.setTitle(movie);
		this.setYear(movie.getYear());
		for(Genre g : movie.getGenres()) {
			this.addSymbolicLink(g);
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
		if (this.year != null) {
			return title+SEPARATOR+year;
		}
		else {
			return title;
		}
	}
	
	public String toJSON() throws IOException {
		if (movie == null) {
			return null;
		}
		return movie.toJSON();
	}
	
	private String setSeparator(String s) {
		String[] strTab = s.split(" ") ;
		String str = new String() ;
		for (int i=0; i<strTab.length-1; i++) {
			str+= strTab[i] + SEPARATOR ;
		}
		return str+strTab[strTab.length-1];
	}
}

