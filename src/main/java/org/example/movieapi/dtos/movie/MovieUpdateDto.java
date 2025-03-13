package org.example.movieapi.dtos.movie;

import lombok.Data;

@Data
public class MovieUpdateDto {
    private String title;
    private String description;
    private String releaseDate;
    private Double rating;
    private String posterPath;
}
