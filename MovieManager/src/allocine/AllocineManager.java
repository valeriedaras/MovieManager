package allocine;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Movie;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.Log;
import allocine.model.CodeName;
import allocine.model.MovieInfos;
import allocine.model.Search;


public class AllocineManager {

	private HttpClient wrapper ;
	
	private AllocineApi api ;
	
	private static boolean verbose = true ;
	
	private static final String PARTNER_KEY = "100043982026";
    
	private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	
	//private final Logger logger = Logger.getLogger("AllocineManager");
	
	private final Log logger = new Log("AllocineManager", verbose);
	
	
	public AllocineManager() {
		try {
			wrapper = HttpClientBuilder.create().build();
			api = new AllocineApi(PARTNER_KEY, SECRET_KEY, wrapper);
		} catch (AllocineException e) {
			logger.logSevere("Creation of AllocineManager failed: {0}",e);
		}
	}
	
	public Movie searchMovies(String query) throws AllocineException, NoMovieFoundException {
		logger.logInfo("Search movie: {0}", query);
		// Call Allocine API to search movies
		Search search = api.searchMovies(query);
		
		// Get found movies
		List<allocine.model.Movie> movies = search.getMovies();
		
		// Check if movie list is empty 
		if (!movies.isEmpty()) {
			logger.logInfo("Found {0} movies: {1}", new Object[]{movies.size()});
			for (allocine.model.Movie movie : movies) {
				logger.logInfo(movie.getOriginalTitle());
				logger.logInfo(movie.getTitle());
			}
			
			// Get first movie code 
			int code = movies.get(0).getCode() ;
			logger.logInfo("Init code: {0}", new Object[]{code});
			
			// Search for the best occurrence
			for(allocine.model.Movie movie : movies) {
				if(query.equals(movie.getTitle())) {
					code = movie.getCode() ;
					logger.logInfo("Change code: {0}", code);
					break;
				}
				if (movie.getOriginalTitle().contains(query)) {
					code = movie.getCode() ;
					logger.logInfo("Change code: {0}", code);
					break;
				}
			}
			
			// Call Allocine API to get all information of selected movie
			MovieInfos nsearch = api.getMovieInfos(Integer.toString(code));
			
			// Create a movie and update its fields
			Movie m = new Movie();
			m.setTitle(nsearch.getMovie().getOriginalTitle());
			m.setYear(Integer.toString(nsearch.getMovie().getProductionYear()));
			m.setSynopsis(nsearch.getMovie().getSynopsis());
			setGenres(m,nsearch.getMovie());
			return m;
		}
		throw new NoMovieFoundException() ;
	}
	
	public void setVerbose(boolean v) {
		verbose = v ;
	}
	
	private void setGenres(Movie nmovie, allocine.model.Movie movie) {
		List<CodeName> genres = movie.getGenre() ;
		for (CodeName genre : genres) {
			nmovie.addGenre(genre.getName());
		}
	}
	
	
	
}
