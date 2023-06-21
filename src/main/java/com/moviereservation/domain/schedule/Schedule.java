package com.moviereservation.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;


@RequiredArgsConstructor
@Getter
@Builder
public class Schedule {
    private final long schedules_seq;
    private final Date date;
    private final String start_time;
    private final long movies_seq;
    private final long theaters_seq;

    public static RowMapper<Schedule> scheduleRowMapper = ((rs, rowNum) -> {
        long seq = rs.getLong("seq");
        long movies_seq = rs.getLong("movies_seq");
        long theaters_seq = rs.getLong("theaters_seq");
        java.sql.Date date = rs.getDate("date");
        String time = rs.getString("start_time");
        return new Schedule(seq, date, time, movies_seq, theaters_seq);
    });

}
