package allocine;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import allocine.model.CodeName;
import allocine.model.MovieInfos;
import allocine.model.Search;


public class AllocineManager {

	private HttpClient wrapper ;
	
	private AllocineApi api ;
	
	private static final String PARTNER_KEY = "100043982026";
    
	private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	
	private static final Logger logger = Logger.getLogger("AllocineManager");
	
	public AllocineManager() {
		try {
			wrapper = HttpClientBuilder.create().build();
			api = new AllocineApi(PARTNER_KEY, SECRET_KEY, wrapper);
		} catch (AllocineException e) {
			logger.log(Level.SEVERE, "Creation of AllocineManager failed:",e);
		}
	}
	
	public Movie getMovieInfos(String query) throws AllocineException, NoMovieFoundException {
		Search search = api.searchMovies(query);

		List<allocine.model.Movie> movies = search.getMovies();
		
		if (!movies.isEmpty()) {
			int code = movies.get(0).getCode() ;
			
			for(allocine.model.Movie movie : movies) {
				if(query.equals(movie.getTitle())) {
					code = movie.getCode() ;
					break;
				}
			}
			MovieInfos nsearch = api.getMovieInfos(Integer.toString(code));
			allocine.model.Movie movie = nsearch.getMovie();
			
			Movie m = new Movie();
			m.setTitle(movie.getOriginalTitle());
			m.setYear(Integer.toString(movie.getProductionYear()));
			m.setSynopsis(movie.getSynopsis());
			setGenres(m,movie);
			return m;
		}
		throw new NoMovieFoundException() ;
	}
	
	private void setGenres(Movie nmovie, allocine.model.Movie movie) {
		List<CodeName> genres = movie.getGenre() ;
		for (CodeName genre : genres) {
			nmovie.addGenre(genre.getName());
		}
	}
	
}
