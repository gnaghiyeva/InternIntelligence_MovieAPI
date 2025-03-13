package org.example.movieapi.config;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieAPIClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/popular?api_key=ac7705b506dacbf997714f23cdcec232";

    public String getPopularMovies() {
        return restTemplate.getForObject(TMDB_API_URL, String.class);
    }
}
