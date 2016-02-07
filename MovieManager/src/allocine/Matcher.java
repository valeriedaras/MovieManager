package allocine;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Matcher {
	
	public Map<List<String>, Integer> match(String query, Collection<List<String>> entry);
	
	public Map<List<String>, Integer> match(List<String> query, Collection<List<String>> entry);
	
}
