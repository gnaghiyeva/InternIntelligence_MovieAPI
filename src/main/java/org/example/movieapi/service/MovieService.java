package org.example.movieapi.service;

import org.example.movieapi.dtos.movie.MovieCreateDto;
import org.example.movieapi.model.Movie;
import org.example.movieapi.payload.ApiResponse;

import java.util.List;

public interface MovieService {
    void fetchAndSaveMoviesFromTMDB();
    List<Movie> getAllMovies();
    ApiResponse findMovieById(Long id);
    ApiResponse createMovie(MovieCreateDto movieCreateDto);

}
