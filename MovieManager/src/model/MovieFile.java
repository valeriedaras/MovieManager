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
	
	private String fileName ;
	
	private List<String> symbolicLinks ;
	
	private static final String SEPARATOR = "_" ;
	
	public MovieFile() {
		this.symbolicLinks = new ArrayList<String>() ;
	}
	
	public String getFileYear() {
		return year;
	}
	
	public void setFileYear(String year) {
		if (year != null) {
			if (year.length() == 4) {
				this.year = year ;
			}
		}
	}
	
	public String getFileTitle() {
		return title;
	}
	
	public String getFileName(){
		return path+fileName;
	}
	
	public void setFileName(String f){
		this.fileName = f;
	}

	private void setFileName(Movie m) throws InvalidMovieFileException{
		if (m != null) {
			if (m.getOriginalTitle() != null) {
				this.title = setSeparator(cleanString(m.getOriginalTitle()));
			}
			else {
				throw new InvalidMovieFileException();
			}
		}
	}
	
	public void setFileTitle(String m) {
		this.title = m ;
	}

	public void setFileExtension(String extension) {
		this.extension = extension;
	}
	
	public String getFileNameWithAbsolutePath(){
		return path+this.getCompleteFileName();
	}
	
	public void setAbsolutePath(String path){
		if(Paths.get(path).toFile().isDirectory()) {
			this.path = path;
		}
		else {
			this.path = Paths.get(path).getParent().toString()+"/";
		}
	}
	
	public List<String> getSymbolicLinks() {
		System.out.println("Links:" + this.symbolicLinks);
		return this.symbolicLinks;
	}
	
	private void addSymbolicLink(Genre g) {
		String gClean = setSeparator(cleanString(g.getName()));
		if (!this.symbolicLinks.contains(gClean)) {
			this.symbolicLinks.add(gClean);
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
			this.setFileName(this.movie);
			this.setFileYear(movie.getYear());
		}
	}
	
	public String toString() {
		return getCompleteFileName() ;
	}
	
	public String getCompleteFileName() {
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
	
	public String getCompleteMovieNameWithAbsolutePath() {
		if(movie != null) {
			if (movie.getYear() != null) {
				return path + setSeparator(cleanString(movie.getOriginalTitle()))+SEPARATOR+movie.getYear()+extension;
			}
			else {
				return path + setSeparator(cleanString(movie.getOriginalTitle())) + extension;
			}
			
		}
		return null;
	}
	
	public String getCompleteMovieName() {
		if(movie != null) {
			if (movie.getYear() != null) {
				return setSeparator(cleanString(movie.getOriginalTitle()))+SEPARATOR+movie.getYear();
			}
			else {
				return setSeparator(cleanString(movie.getOriginalTitle()));
			}
		}
		return null;
	}
	
	public String getCompleteMovieNameWithExt() {
		if(movie != null) {
			if (movie.getYear() != null) {
				return setSeparator(cleanString(movie.getOriginalTitle()))+SEPARATOR+movie.getYear()+extension;
			}
			else {
				return setSeparator(cleanString(movie.getOriginalTitle()))+ extension;
			}
		}
		return null;
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

}

