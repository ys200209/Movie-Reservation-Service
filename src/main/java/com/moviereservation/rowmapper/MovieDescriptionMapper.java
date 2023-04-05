package com.moviereservation.rowmapper;

import com.moviereservation.domain.MovieDescription;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDescriptionMapper implements RowMapper<MovieDescription> {
    @Override
    public MovieDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        MovieDescription movieDescription = new MovieDescription(
                rs.getLong("movies_seq"),
                rs.getLong("categories_seq"),
                rs.getString("story"),
                rs.getInt("running_time"),
                rs.getString("director"),
                rs.getString("actor"),
                rs.getInt("age_limit")
        );
        return movieDescription;
    }
}
