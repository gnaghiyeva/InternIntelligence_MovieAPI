package org.example.movieapi.repository;

import org.example.movieapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate")
    List<Movie> findByReleaseDate(@Param("releaseDate") String releaseDate);
}
