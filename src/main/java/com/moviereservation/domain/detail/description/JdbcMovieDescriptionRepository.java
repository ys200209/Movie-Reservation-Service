package com.moviereservation.domain.detail.description;

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

    private final String SELECT_MOVIE_DESCRIPTION_BY_MOVIES_ID_SQL = "select m.movie_name, m.poster, c.name, md.story, md.running_time, md.director, md.actor, md.age_limit " +
            "from movie_descriptions md inner join movies m on m.seq = md.movies_seq inner join categories c on md.categories_seq = c.seq " +
            "where md.movies_seq = ?";

    @Override
    public MovieDescription findByMovieId(Long id) {
        MovieDescription movieDescription = jdbcTemplate.queryForObject(SELECT_MOVIE_DESCRIPTION_BY_MOVIES_ID_SQL, new RowMapper<MovieDescription>() {
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
        return movieDescription;
    }
}
