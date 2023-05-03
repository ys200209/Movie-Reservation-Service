package com.moviereservation.domain.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository {
    public static final String SELECT_MOVIE_QUERY = "SELECT * FROM movies";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MoviePreviewDto> getAllMovies() {
        List<MoviePreviewDto> movieDtoList = new ArrayList<MoviePreviewDto>();

        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIE_QUERY, (rs, rowNum) -> {
            Long seq = rs.getLong("seq");
            String movieName = rs.getString("movie_name");
            String poster = rs.getString("poster");
            return new Movie(seq, movieName, poster);
        });

        for (Movie movie : movies) {
            movieDtoList.add(new MoviePreviewDto(movie.getSeq(), movie.getMovieName(), movie.getPoster()));
        }

        return movieDtoList;
    }
}
