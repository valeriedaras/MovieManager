package allocine;

import java.util.List;

public interface Matcher {
	
	public void match(String query, String opQuery, List<Result> res) ;
	
	public void match(List<String> query, String opQuery, List<Result> res) ;
	
}
