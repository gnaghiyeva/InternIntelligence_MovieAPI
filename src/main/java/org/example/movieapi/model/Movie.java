package org.example.movieapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String releaseDate;
    private double rating;
    private String posterPath;
    private String originalLanguage;
    private double popularity;

}
