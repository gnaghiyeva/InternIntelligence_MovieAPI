package org.example.movieapi.controller;

import org.example.movieapi.dtos.movie.MovieCreateDto;
import org.example.movieapi.model.Movie;
import org.example.movieapi.payload.ApiResponse;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "id")
    public ResponseEntity<ApiResponse> getMovieById(@RequestParam Long id) {
        ApiResponse movieDetail = movieService.findMovieById(id);
        return ResponseEntity.ok(movieDetail);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createMovie(@ModelAttribute MovieCreateDto movieCreateDto) {
        ApiResponse movies = movieService.createMovie(movieCreateDto);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }
}
