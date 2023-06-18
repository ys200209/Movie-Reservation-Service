package com.moviereservation.domain.movie.repository;

import com.moviereservation.domain.movie.controller.dto.MovieRegisterDto;
import com.moviereservation.domain.movie.Movie;
import com.moviereservation.domain.movie.controller.dto.MoviePreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcMovieRepository implements MovieRepository {

    public static final String SELECT_MOVIE_QUERY = "SELECT * FROM movies";

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_MOVIE_SQL = "INSERT INTO MOVIES(MOVIE_NAME, POSTER) VALUES(?,?)";

    private final String SELECT_MOVIE_BY_ID_QUERY = "SELECT * FROM movies WHERE movies_seq = ?";

    @Override
    public long save(MovieRegisterDto data) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    INSERT_MOVIE_SQL, new String[] {"seq"}
            );
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, "/images/" + data.getPoster().getOriginalFilename());
            return  preparedStatement;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        return keyValue.longValue();
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIE_QUERY, (rs, rowNum) -> {
            Long seq = rs.getLong("seq");
            String movieName = rs.getString("movie_name");
            String poster = rs.getString("poster");
            return new Movie(seq, movieName, poster);
        });
        return movies;
    }

    @Override
    public Movie findById(long id) {
        Movie movie = jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID_QUERY, (rs, rowNum) ->{
            Long seq = rs.getLong("seq");
            String movieName = rs.getString("movie_name");
            String poster = rs.getString("poster");
            return new Movie(seq, movieName, poster);
        });
        return movie;
    }
}
