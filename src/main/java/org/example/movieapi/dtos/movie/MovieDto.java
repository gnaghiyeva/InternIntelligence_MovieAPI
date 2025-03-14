package org.example.movieapi.dtos.movie;

import lombok.Data;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String releaseDate;
    private Double rating;
    private String posterPath;
    private String originalLanguage;
    private double popularity;
}
