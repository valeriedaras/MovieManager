package test.allocine;

import java.util.List;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import allocine.AllocineApi;
import allocine.AllocineException;
import allocine.model.CodeName;
import allocine.model.Movie;
import allocine.model.MovieInfos;
import allocine.model.Search;

public class Launch {

	private static final String PARTNER_KEY = "100043982026";
    private static final String SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";
	
	public static void main(String[] args) {
		HttpClient wrapper = HttpClientBuilder.create().build();
		
		try {
			AllocineApi api = new AllocineApi(PARTNER_KEY, SECRET_KEY, wrapper);
			Search search = api.searchMovies("star wars");
			List<Movie> movies = search.getMovies();
			
			int code = movies.get(0).getCode();
			MovieInfos search2 = api.getMovieInfos(Integer.toString(code));
			Movie movie = search2.getMovie();
			System.out.println("*************************");
			System.out.println("Original title:" + movie.getOriginalTitle());
			System.out.println("Title:" + movie.getTitle());
			System.out.println("Year:" + movie.getProductionYear());
			System.out.println("Synopsis:" + movie.getSynopsis());
			List<CodeName> genres = movie.getGenre() ;
			String genresStr = new String();
			for (CodeName genre : genres) {
				genresStr = genresStr +" "+ genre.getName()  ;
			}
			System.out.println("Genre:" + genresStr);
			System.out.println("");
			
			/*
			MovieInfos search2 = api.getMovieInfos("61282");
			Movie movie = search2.getMovie();
			System.out.println("*************************");
			System.out.println("Original title:" + movie.getOriginalTitle());
			System.out.println("Title:" + movie.getTitle());
			System.out.println("Year:" + movie.getProductionYear());
			System.out.println("Synopsis:" + movie.getSynopsis());
			System.out.println("Short Synopsis:" + movie.getSynopsisShort());
			System.out.println("");

			search2 = api.getMovieInfos("5818");
			movie = search2.getMovie();
			System.out.println("*************************");
			System.out.println("Original title:" + movie.getOriginalTitle());
			System.out.println("Title:" + movie.getTitle());
			System.out.println("Year:" + movie.getProductionYear());
			System.out.println("Synopsis:" + movie.getSynopsis());
			System.out.println("Short Synopsis:" + movie.getSynopsisShort());
			System.out.println("");
			*/
			
		} catch (AllocineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
