package com.moviereservation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class MovieDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getAllMovieNames() {
        String query = "SELECT * FROM movies";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Long seq = rs.getLong("seq");
            String movieName = rs.getString("movie_name");
            String poster = rs.getString("poster");
            return new Movie(movieName, poster);
        });
    }
}