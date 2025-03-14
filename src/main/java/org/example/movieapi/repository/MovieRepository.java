package org.example.movieapi.repository;

import org.example.movieapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Movie> findByTitle(@Param("title") String title);

    @Query("SELECT m FROM Movie m WHERE " +
            "FUNCTION('YEAR', m.releaseDate) = :year " +
            "AND m.popularity >= :minPopularity " +
            "AND m.rating >= :minVote")
    List<Movie> filterMovies(@Param("year") int year,
                             @Param("minPopularity") double minPopularity,
                             @Param("minVoteAverage") double minVoteAverage);
}
