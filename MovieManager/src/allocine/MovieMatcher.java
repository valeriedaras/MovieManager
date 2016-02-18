package allocine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.MovieFile;
import allocine.model.Movie;


public class MovieMatcher implements Matcher {

	public void match(String query, String opQuery, List<Result> res) {
		this.match(Arrays.asList(query.toLowerCase().split("[\\s\\p{Punct}]+")), opQuery, res);
	}
	
	public void match(List<String> query, String opQuery, List<Result> results) {
		int index = results.size() ;
		for(Result res : results) {
			Integer score = index ;
			for (String term : query) {
				if (res.getOriginalTitle().contains(term)) {
					res.setScore(score += 1) ;
				}
				if (res.getTitle()!=null) {
					if(res.getTitle().contains(term)) {
						res.setScore(score += 1) ;
					}
				}
			}
			if (res.getYear()!=null) {
				if (res.getYear().equals(opQuery)) {
					res.setScore(score += 10) ;
				}
			}
			if (res.getOriginalTitle().equals(query)) {
				res.setScore(score += 10) ;
			}
			if (res.getTitle()!=null) {
				if (res.getTitle().equals(query)) {
					res.setScore(score += 10) ;
				}
			}
		}
		index-- ;
	}
	
	
	public int getBestEntry(MovieFile query, List<Movie> entry) {
		
		// Retrieving all titles, production years and movie codes
		List<Result> results = new ArrayList<Result>() ;
		for (Movie m : entry) {
			Result result = new Result() ;
			result.setCode(m.getCode());
			if (m.getOriginalTitle() != null) {
				result.setOriginalTitle(Arrays.asList(m.getOriginalTitle().toLowerCase().split("[\\s\\p{Punct}]+")));
			}
			if(m.getTitle() != null) {
				result.setTitle(Arrays.asList(m.getTitle().toLowerCase().split("[\\s\\p{Punct}]+")));
			}
			if (m.getProductionYear() > 1920) {
				result.setYear(Integer.toString(m.getProductionYear()));
			}
			results.add(result);
		}
		
		
		// Match
		this.match(query.getFileTitle(), query.getFileYear(), results) ;
		
		// Init movie code of best entry
		int code = -1 ;
				
		// Init maximum score
		int max = 0 ;
		
		// Get best score
		for(Result res : results) {
			if(res.getScore() > max) {
				max = res.getScore();
				code = res.getCode();
			}
		}
		return code;
	}

}
