package org.example.movieapi.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.movieapi.config.Mapper;
import org.example.movieapi.config.MovieAPIClient;
import org.example.movieapi.dtos.movie.MovieCreateDto;
import org.example.movieapi.dtos.movie.MovieDto;
import org.example.movieapi.model.Movie;
import org.example.movieapi.payload.ApiResponse;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieAPIClient movieAPIClient;

    @Autowired
    private ModelMapper modelMapper;

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
                movie.setReleaseDate(movieNode.get("release_date").asText());
                movie.setRating(movieNode.get("vote_average").asDouble());
                movie.setPosterPath(movieNode.get("poster_path").asText());
//                movie.setGenre(movieNode.get("genre").asText());
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


    @Override
    public ApiResponse findMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            return new ApiResponse(false, "Movie not found");
        }
        MovieDto movieDto = modelMapper.map(optionalMovie.get(), MovieDto.class);
        return new ApiResponse(true,"Project found", movieDto);
    }

    @Override
    public ApiResponse createMovie(MovieCreateDto movieCreateDto) {
        Movie movie = modelMapper.map(movieCreateDto, Movie.class);
        movieRepository.save(movie);
        return new ApiResponse(true,"Movie created", movie);
    }
}
