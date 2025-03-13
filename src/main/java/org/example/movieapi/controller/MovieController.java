package org.example.movieapi.controller;

import org.example.movieapi.model.Movie;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/fetch-from-tmdb")
    public ResponseEntity<String> fetchMoviesFromTMDB() {
        movieService.fetchAndSaveMoviesFromTMDB();
        return ResponseEntity.ok("Movies fetched from TMDB and saved to database.");
    }
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
