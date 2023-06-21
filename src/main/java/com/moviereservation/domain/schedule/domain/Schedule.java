package com.moviereservation.domain.schedule.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
}
