package org.example.movieapi.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.movieapi.config.MovieAPIClient;
import org.example.movieapi.model.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieAPIClient movieAPIClient;

    @Override
    public void fetchAndSaveMoviesFromTMDB() {
        try {
            String response = movieAPIClient.getPopularMovies();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            List<Movie> movieList = new ArrayList<>();

            for (JsonNode movieNode : root.get("results")) {
                Movie movie = new Movie();
                movie.setTitle(movieNode.get("title").asText());
                movie.setDescription(movieNode.get("overview").asText());
                movie.setReleaseDate(LocalDate.parse(movieNode.get("release_date").asText()));
                movie.setRating(movieNode.get("vote_average").asDouble());
                movie.setPosterPath(movieNode.get("poster_path").asText());
                movieList.add(movie);
            }
            movieRepository.saveAll(movieList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
