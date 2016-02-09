package manager;

import utils.Log;
import allocine.AllocineException;
import allocine.AllocineManager;
import allocine.NoMovieFoundException;

public class MovieManager {
	
	/**
	 * Logger
	 */
	private static final Log logger = new Log("MovieManager", false);
	
	public static void main(String[] args) {
		try {
			AllocineManager api = new AllocineManager() ;
			model.Movie movie = api.searchMovies("harry potter");
			
			System.out.println("*************************");
			System.out.println(movie);
			movie = api.searchMovies("harry potter 5");
			System.out.println(movie);
		} catch (AllocineException e) {
			logger.logSevere("Allocine Exception:", e);
		} catch (NoMovieFoundException e) {
			logger.logSevere("No movie found.");
		}
	}

}
