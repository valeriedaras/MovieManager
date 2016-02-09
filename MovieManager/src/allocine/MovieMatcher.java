package allocine;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import allocine.model.Movie;


public class MovieMatcher implements Matcher {
	
	@Override
	public Map<List<String>, Integer> match(String query, Collection<List<String>> entry) {
		return this.match(Arrays.asList(query.toLowerCase().split("[\\s\\p{Punct}]+")), entry);
	}


	public Map<List<String>, Integer> match(List<String> terms, Collection<List<String>> entry) {
		Map<List<String>, Integer> scores = new HashMap<List<String>, Integer>();
		int index = entry.size() ;
		for (List<String> e : entry) {
			Integer score = index ;
			for (String term : terms) {
				if (e.contains(term)) {
					score += 1 ;
					scores.put(e, score);				
				}
				
			}
			if (e.equals(terms)) {
				score += 10 ;
				scores.put(e, score);
			}
			index-- ;
		}
		
		return scores;
	}
	
	public int getBestEntry(String query, List<Movie> entry) {
		int code = -1 ;
		int max = 0 ;
		
		Map<List<String>, Integer> entries = new HashMap<List<String>, Integer>() ;
		for (Movie m : entry) {
			if (m.getOriginalTitle() != null) {
				entries.put(Arrays.asList(m.getOriginalTitle().toLowerCase().split("[\\s\\p{Punct}]+")), m.getCode());
			}
			if(m.getTitle() != null) {
				entries.put(Arrays.asList(m.getTitle().toLowerCase().split("[\\s\\p{Punct}]+")), m.getCode());
			}
		}
		
		// Match
		Map<List<String>, Integer> map = this.match(query, entries.keySet());
		
		// Get Movie with the best score
		Set<Entry<List<String>, Integer>> e = map.entrySet();
		for (Entry<List<String>, Integer> l : e) {
			if (l.getValue() > max) {
				max = l.getValue() ;
				code = entries.get(l.getKey()) ;
			}
		}
		return code ;
	}

}
