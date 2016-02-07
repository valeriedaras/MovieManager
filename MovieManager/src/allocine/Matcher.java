package allocine;

import java.util.List;
import java.util.Map;

public interface Matcher {
	
	public Map<List<String>, Integer> match(String query, List<String> entry);
	
	public Map<List<String>, Integer> match(List<String> query, List<List<String>> entry);
	
}
