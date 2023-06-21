package com.moviereservation.domain.movie;

import com.moviereservation.web.movie.dto.MovieDescriptionDto;
import com.moviereservation.web.movie.dto.MovieRegisterDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcMovieRepository implements MovieRepository {
    public static final String SELECT_MOVIE_QUERY = "SELECT * FROM movies";
    private final String INSERT_MOVIE_SQL = "INSERT INTO MOVIES(MOVIE_NAME, POSTER) VALUES(?,?)";
    private final String SELECT_MOVIE_BY_ID_QUERY = "SELECT * FROM movies WHERE movies_seq = ?";
    private final String INSERT_MOVIE_DESCRIPTION = "INSERT INTO MOVIE_DESCRIPTIONS(MOVIES_SEQ, CATEGORIES_SEQ, STORY, RUNNING_TIME, DIRECTOR, ACTOR, AGE_LIMIT) VALUES(?, ?, ?, ?, ?, ?, ?)";


    private final String SELECT_MOVIE_DESCRIPTION_BY_MOVIE_ID_SQL = "select m.movie_name, m.poster, c.name, md.story, md.running_time, md.director, md.actor, md.age_limit " +
            "from movie_descriptions md inner join movies m on m.seq = md.movies_seq inner join categories c on md.categories_seq = c.seq " +
            "where md.movies_seq = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public MovieDescriptionDto findByMovieId(Long id) {
        MovieDescription movieDescription = jdbcTemplate.queryForObject(SELECT_MOVIE_DESCRIPTION_BY_MOVIE_ID_SQL, new RowMapper<MovieDescription>() {
            @Override
            public MovieDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
                MovieDescription movieDescription = new MovieDescription(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
                return movieDescription;
            }
        }, id);
        return new MovieDescriptionDto(movieDescription);
    }

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
    public void save(MovieRegisterDto data, long movies_seq, long categories_seq) {
        int a = jdbcTemplate.update(INSERT_MOVIE_DESCRIPTION, movies_seq,
                categories_seq, data.getStory(), Integer.parseInt(data.getRunningTime()), data.getDirector(), data.getActor(), Integer.parseInt(data.getAgeLimit()));
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
