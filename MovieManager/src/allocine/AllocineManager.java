package allocine;

import java.util.List;

import model.Movie;
import model.MovieFile;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import allocine.model.CastMember;
import allocine.model.CodeName;
import allocine.model.MovieInfos;
import allocine.model.Search;


public class AllocineManager {

	private MovieMatcher matcher ;
	
	private HttpClient wrapper ;
	
	private AllocineApi api ;
	
	private static final String PARTNER_KEY = "100043982026";
    
	private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	
	private static Logger logger = LogManager.getLogger("AllocineManager");
	
	/**
	 * Constructor
	 */
	public AllocineManager() {
		try {
			matcher = new MovieMatcher() ;
			wrapper = HttpClientBuilder.create().build();
			api = new AllocineApi(PARTNER_KEY, SECRET_KEY, wrapper);
		} catch (AllocineException e) {
			logger.fatal("Creation of AllocineManager failed: {0}. The system will be stopped.",e);
			System.exit(-1);
		}
	}
	
	/**
	 * Method to search movies
	 * @param query: movieFile containing necessary information
	 * @return the best occurrence of all found movies
	 * @throws AllocineException
	 * @throws NoMovieFoundException
	 */
	public Movie searchMovies(MovieFile query) throws AllocineException, NoMovieFoundException {
		// Call Allocine API to search movies
		Search search = api.searchMovies(query.getFileTitle());
		
		// Get found movies
		List<allocine.model.Movie> movies = search.getMovies();
		
		// Check if movie list is empty 
		if (!movies.isEmpty()) {
			for (allocine.model.Movie movie : movies) {
				logger.debug(movie.getOriginalTitle());
				logger.debug(movie.getTitle());
			}
			
			// Search for the best occurrence
			int code = matcher.getBestEntry(query, movies) ;
			if (code > 0) {
				// Call Allocine API to get all information of selected movie
				MovieInfos nsearch = api.getMovieInfos(Integer.toString(code));
				
				// Create a movie and update its fields
				Movie m = new Movie();
				m.setTitle(nsearch.getMovie().getTitle());
				m.setOriginalTitle(nsearch.getMovie().getOriginalTitle());
				m.setYear(Integer.toString(nsearch.getMovie().getProductionYear()));
				m.setSynopsis(nsearch.getMovie().getSynopsis());
				setCastingMember(m,nsearch.getMovie());
				setGenres(m,nsearch.getMovie());
				return m;
			}
		}
		throw new NoMovieFoundException() ;
	}
	
	/**
	 * Method to add all genres to the movie
	 * @param nmovie: new movie
	 * @param movie: movie from allocine
	 */
	private void setGenres(Movie nmovie, allocine.model.Movie movie) {
		List<CodeName> genres = movie.getGenre() ;
		for (CodeName genre : genres) {
			nmovie.addGenre(genre.getName());
		}
	}
	
	/**
	 * Method to add all casting members to the movie
	 * @param nmovie: new movie
	 * @param movie: movie from allocine
	 */
	private void setCastingMember(Movie nmovie, allocine.model.Movie movie) {
		List<CastMember> members = movie.getCastMember() ;
		for (CastMember member : members) {
			if (member.isActor()) {
				nmovie.addActor(member.getShortPerson().getName(),member.getShortPerson().getGender());
			}
			else if (member.isDirector()) {
				nmovie.addDirector(member.getShortPerson().getName(),member.getShortPerson().getGender());
			}
		}
	}
	
}
