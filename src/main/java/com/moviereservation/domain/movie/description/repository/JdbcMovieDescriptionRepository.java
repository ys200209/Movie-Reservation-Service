package com.moviereservation.domain.movie.description.repository;

import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import com.moviereservation.domain.movie.description.domain.MovieDescription;
import com.moviereservation.domain.movie.description.dto.MovieDescriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDescriptionRepository implements MovieDescriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_MOVIE_DESCRIPTION_BY_MOVIE_ID_SQL = "select m.movie_name, m.poster, c.name, md.story, md.running_time, md.director, md.actor, md.age_limit " +
            "from movie_descriptions md inner join movies m on m.seq = md.movies_seq inner join categories c on md.categories_seq = c.seq " +
            "where md.movies_seq = ?";

    private final String INSERT_MOVIE_DESCRIPTION = "INSERT INTO MOVIE_DESCRIPTIONS(MOVIES_SEQ, CATEGORIES_SEQ, STORY, RUNNING_TIME, DIRECTOR, ACTOR, AGE_LIMIT) VALUES(?, ?, ?, ?, ?, ?, ?)";

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
    public void save(MovieRegisterDto data, long movies_seq, long categories_seq) {
        int a = jdbcTemplate.update(INSERT_MOVIE_DESCRIPTION, movies_seq,
                categories_seq, data.getStory(), Integer.parseInt(data.getRunningTime()), data.getDirector(), data.getActor(), Integer.parseInt(data.getAgeLimit()));
    }
}
