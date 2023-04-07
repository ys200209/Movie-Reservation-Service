package com.moviereservation.rowmapper;

import com.moviereservation.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie(
                rs.getLong("seq"),
                rs.getString("movie_name"),
                rs.getString("poster")
        );
        return movie;
    }
}
