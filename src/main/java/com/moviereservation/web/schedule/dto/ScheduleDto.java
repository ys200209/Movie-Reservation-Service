package com.moviereservation.web.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
@Getter
public class ScheduleDto {
    private final long schedules_seq;
    private final Date date;
    private final String start_time;
    private final long theaters_seq;
}
