package com.moviereservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {
    public static final String SELECT_MOVIE_QUERY = "SELECT * FROM movies";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MoviePreviewDto> getAllMovieNames() {

        return jdbcTemplate.query(SELECT_MOVIE_QUERY, (rs, rowNum) -> {
            Long seq = rs.getLong("seq");
            String movieName = rs.getString("movie_name");
            String poster = rs.getString("poster");
            return new MoviePreviewDto(seq, movieName, poster);
        });
    }
}