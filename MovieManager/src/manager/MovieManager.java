package manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import allocine.AllocineException;
import allocine.AllocineManager;
import allocine.NoMovieFoundException;

public class MovieManager {
	
	private static final Logger logger = Logger.getLogger("MovieManager");
	
	public static void main(String[] args) {
		try {
			AllocineManager api = new AllocineManager() ;
			model.Movie movie = api.searchMovies("star wars I");
			
			System.out.println("*************************");
			System.out.println(movie);
			
		} catch (AllocineException e) {
			logger.log(Level.SEVERE, "Allocine Exception:", e);
		} catch (NoMovieFoundException e) {
			logger.log(Level.INFO, "No movie found.");
		}
	}

}
