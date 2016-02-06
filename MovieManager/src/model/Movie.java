package model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

	private String title ;
	
	private String year ;
	
	private Format format ;
	
	private List<Genre> genres ;
	
	private String synopsis ;
	
	public Movie() {
		this.genres = new ArrayList<Genre>() ;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	public void addGenre(String genre) {
		if(!this.genres.contains(new Genre(genre))) {
			this.genres.add(new Genre(genre));
		}
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String toString() {
		String genres = new String() ;
		for (Genre g : this.genres) {
			genres += "["+g.getName() +"] " ;
		}
		return "Original Title: " + title 
				+"\nYear: " + year
				+"\nSynopsis: " + synopsis
				+"\nGenre: " + genres;
	}
}
