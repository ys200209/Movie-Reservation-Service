package com.moviereservation.domain.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_SCHEDULES_BY_MOVIE_ID_AND_DATE_SQL = "SELECT * FROM SCHEDULES WHERE DATE = ? AND MOVIES_SEQ = ?";
    private final String SELECT_MOVIE_ID_BY_SEQ_SQL = "SELECT * FROM SCHEDULES WHERE SEQ = ?";

    @Override
    public List<Schedule> findByMovieIdAndDate(long id, String input_date) {
        List<Schedule> schedules = jdbcTemplate.query(SELECT_SCHEDULES_BY_MOVIE_ID_AND_DATE_SQL, Schedule.scheduleRowMapper, input_date, id);
        return schedules;
    }

    @Override
    public List<Schedule> findBySeq(long seq) {
        return jdbcTemplate.query(SELECT_MOVIE_ID_BY_SEQ_SQL, Schedule.scheduleRowMapper, seq);
    }
}
