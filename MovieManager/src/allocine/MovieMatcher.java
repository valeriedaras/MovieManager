package allocine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MovieMatcher implements Matcher {
	
	@Override
	public Map<List<String>, Integer> match(String query, List<String> entry) {
		List<List<String>> entries = new ArrayList<List<String>>() ;
		for (String e : entry) {
			entries.add(Arrays.asList(e.split("[\\s\\p{Punct}]+")));
		}
		return this.match(Arrays.asList(query.split("[\\s\\p{Punct}]+")), entries);
	}


	public Map<List<String>, Integer> match(List<String> terms, List<List<String>> entry) {
		Map<List<String>, Integer> scores = new HashMap<List<String>, Integer>();
		
		for (List<String> e : entry) {
			Integer score = 0 ;
			for (String term : terms) {
				if (e.contains(term)) {
					score += 1 ;
					scores.put(e, score);				
				}
			}
		}
		
		return scores;
	}
	
	public List<String> getBestEntry(String query, List<String> entry) {
		List<String> list = new ArrayList<String>() ;
		int max = 0 ;
		
		Map<List<String>, Integer> map = this.match(query, entry);
		Set<Entry<List<String>, Integer>> e = map.entrySet();
		for (Entry<List<String>, Integer> l : e) {
			if (l.getValue() > max) {
				max = l.getValue() ;
				list = l.getKey() ;
			}
		}
		return list ;
	}

	
	public static void main(String[] args) {
		MovieMatcher mm = new MovieMatcher() ;
		
		String query = "Star Wars I" ;
		List<String> entries = new ArrayList<String>() ;
		entries.add("Star Wars: Episode III - Revenge of the Sith");
		entries.add("Star Wars: Episode I - The Phantom Menace");
		entries.add("Star Wars: Episode II - Attack of the Clones");
		entries.add("Dünyayi Kurtaran Adam - Turkish Star Wars");
		entries.add("Robot Chicken: Star Wars Episode III");
		
		List<String> l = mm.getBestEntry(query, entries) ;
		System.out.println("best:" + l);
	}
	

}
