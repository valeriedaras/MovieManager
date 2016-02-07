package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {

	private String originalTitle ;
	
	private String title ;
	
	private String year ;
	
	private List<Director> directors ;
	
	private Format format ;
	
	private List<Actor> actors ;
	
	private List<Genre> genres ;
	
	private String synopsis ;
	
	public Movie() {
		this.genres = new ArrayList<Genre>() ;
		this.actors = new ArrayList<Actor>() ;
		this.directors = new ArrayList<Director>() ;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String title) {
		this.originalTitle = title;
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

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public void addActor(String actor, int g) {
		List<String> ac = Arrays.asList(actor.split(" "));
		switch (ac.size()) {
		case 0:
			break;
		case 1:
			actors.add(new Actor(ac.get(0),g));
			break;
		default:
			actors.add(new Actor(ac.get(0), ac.get(ac.size()-1),g));				
		}
	}
	
	public List<Director> getDirector() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}
	
	public void addDirector(String dir, int g) {
		List<String> ac = Arrays.asList(dir.split(" "));
		switch (ac.size()) {
		case 0:
			break;
		case 1:
			directors.add(new Director(ac.get(0),g));
			break;
		default:
			directors.add(new Director(ac.get(0), ac.get(ac.size()-1),g));				
		}
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
				+"\nGenre: " + genres
				+"\nActors: " + actors
				+"\nDirectors: " + directors;
	}
}
