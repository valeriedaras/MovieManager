package allocine;

import java.util.List;
import model.Movie;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.Log;
import allocine.model.CodeName;
import allocine.model.MovieInfos;
import allocine.model.Search;


public class AllocineManager {

	private MovieMatcher matcher ;
	
	private HttpClient wrapper ;
	
	private AllocineApi api ;
	
	private static boolean verbose = false ;
	
	private static final String PARTNER_KEY = "100043982026";
    
	private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	
	private final Log logger = new Log("AllocineManager", verbose);
	
	
	public AllocineManager() {
		try {
			matcher = new MovieMatcher() ;
			wrapper = HttpClientBuilder.create().build();
			api = new AllocineApi(PARTNER_KEY, SECRET_KEY, wrapper);
		} catch (AllocineException e) {
			logger.logSevere("Creation of AllocineManager failed: {0}",e);
		}
	}
	
	public Movie searchMovies(String query) throws AllocineException, NoMovieFoundException {
		// Call Allocine API to search movies
		Search search = api.searchMovies(query);
		
		// Get found movies
		List<allocine.model.Movie> movies = search.getMovies();
		
		// Check if movie list is empty 
		if (!movies.isEmpty()) {
			for (allocine.model.Movie movie : movies) {
				logger.logInfo(movie.getOriginalTitle());
				logger.logInfo(movie.getTitle());
			}
			
			// Get first movie code 
			int code = movies.get(0).getCode() ;
			
			// Search for the best occurrence
			for(allocine.model.Movie movie : movies) {
				if(query.equals(movie.getTitle())) {
					code = movie.getCode() ;
					break;
				}
				if (movie.getOriginalTitle().contains(query)) {
					code = movie.getCode() ;
					break;
				}
			}
			code = matcher.getBestEntry(query, movies) ;
						
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
