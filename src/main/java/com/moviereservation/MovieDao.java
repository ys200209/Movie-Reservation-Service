package com.moviereservation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class MovieDao {
    public static final String SELECT_MOVIE_QUERY = "SELECT * FROM movies";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getAllMovieNames() {

        return jdbcTemplate.query(SELECT_MOVIE_QUERY, (rs, rowNum) -> {
            Long seq = rs.getLong("seq");
            String movieName = rs.getString("movie_name");
            String poster = rs.getString("poster");
            return new Movie(seq, movieName, poster);
        });
    }
}