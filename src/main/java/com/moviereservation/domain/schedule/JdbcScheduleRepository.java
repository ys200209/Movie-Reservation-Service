package com.moviereservation.domain.schedule;

import com.moviereservation.domain.movie.repository.MovieRepository;
import com.moviereservation.domain.schedule.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_SCHEDULES_BY_MOVIE_ID_AND_DATE_SQL = "SELECT * FROM SCHEDULES WHERE DATE = ? AND MOVIES_SEQ = ?";

    @Override
    public List<Schedule> findByMovieIdAndDate(long id, String input_date) {
        List<Schedule> schedules = jdbcTemplate.query(SELECT_SCHEDULES_BY_MOVIE_ID_AND_DATE_SQL, (rs, rowNum) -> {
                long seq = rs.getLong("seq");
                long movies_seq = rs.getLong("movies_seq");
                long theaters_seq = rs.getLong("theaters_seq");
                java.sql.Date date = rs.getDate("date");
                String time = rs.getString("start_time");
                return new Schedule(seq, date, time, movies_seq, theaters_seq);
        }, input_date, id);
        return schedules;
    }
}
