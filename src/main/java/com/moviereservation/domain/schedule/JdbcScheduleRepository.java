package com.moviereservation.domain.schedule;

import com.moviereservation.web.schedule.dto.AddScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_SCHEDULES_BY_MOVIE_ID_AND_DATE_SQL = "SELECT * FROM SCHEDULES WHERE DATE = ? AND MOVIES_SEQ = ?";
    private final String SELECT_MOVIE_ID_BY_SEQ_SQL = "SELECT * FROM SCHEDULES WHERE SEQ = ?";
    private final String INSERT_SCHEDULE_SQL = "INSERT INTO SCHEDULES(MOVIES_SEQ, DATE, START_TIME, THEATERS_SEQ) VALUES(?, ?, ?, ?)";
    private final String INSERT_SEATS_SQL = "INSERT INTO SEATS(SCHEDULES_SEQ, SEAT_ROW, SEAT_COLUMN, STATUS) VALUES(?, ?, ?, ?)";

    @Override
    public List<Schedule> findByMovieIdAndDate(long id, String input_date) {
        List<Schedule> schedules = jdbcTemplate.query(SELECT_SCHEDULES_BY_MOVIE_ID_AND_DATE_SQL, Schedule.scheduleRowMapper, input_date, id);
        return schedules;
    }

    @Override
    public List<Schedule> findBySeq(long seq) {
        return jdbcTemplate.query(SELECT_MOVIE_ID_BY_SEQ_SQL, Schedule.scheduleRowMapper, seq);
    }

    @Override
    public long save(AddScheduleDto dto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    INSERT_SCHEDULE_SQL, new String[] {"seq"}
            );
            preparedStatement.setLong(1, dto.getMovies_seq());
            preparedStatement.setDate(2, Date.valueOf(LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)));
            preparedStatement.setString(3, dto.getStart_time());
            preparedStatement.setLong(4, dto.getTheaters_seq());
            return  preparedStatement;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        return keyValue.longValue();
    }

    @Override
    public void createSeats(long scheduleSeq) {
        for(int i = 1; i<7; i++){
            for(int j =1; j<10; j++){
                if(j==3 || j==7){
                    jdbcTemplate.update(INSERT_SEATS_SQL, scheduleSeq,i, j, "empty");
                }else{
                    jdbcTemplate.update(INSERT_SEATS_SQL, scheduleSeq,i, j, "normal");
                }
            }
        }
    }
}
