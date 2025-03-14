package org.example.movieapi.controller;

import org.example.movieapi.dtos.movie.MovieCreateDto;
import org.example.movieapi.dtos.movie.MovieUpdateDto;
import org.example.movieapi.model.Movie;
import org.example.movieapi.payload.ApiResponse;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

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

    @PutMapping(value = "/id", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateMovie(@RequestParam Long id, @ModelAttribute MovieUpdateDto movieUpdateDto){
        ApiResponse response = movieService.updateMovie(id, movieUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<ApiResponse> deleteMovie(@RequestParam Long id) {
        ApiResponse response = movieService.deleteMovie(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String title) {
        return movieRepository.findByTitle(title);
    }

    @GetMapping("/filter")
    public List<Movie> filterByYearPopularityVote(@RequestParam int year, @RequestParam(required = false) Double minPopularity, @RequestParam(required = false) Double minVoteAverage) {
        List<Movie> allMovies = movieRepository.findAll();

        return allMovies.stream()
                .filter(movie -> {
                    try {
                        LocalDate releaseDate = LocalDate.parse(movie.getReleaseDate(), DateTimeFormatter.ISO_DATE);
                        return releaseDate.getYear() == year;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .filter(movie -> minPopularity == null || movie.getPopularity() >= minPopularity)
                .filter(movie -> minVoteAverage == null || movie.getRating() >= minVoteAverage)
                .collect(Collectors.toList());
    }
}
