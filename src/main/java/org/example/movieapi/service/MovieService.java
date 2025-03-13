package org.example.movieapi.service;

import org.example.movieapi.model.Movie;

import java.util.List;

public interface MovieService {
    void fetchAndSaveMoviesFromTMDB();
    List<Movie> getAllMovies();
}
