package com.moviereservation.domain.schedule;

import com.moviereservation.domain.schedule.domain.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findByMovieIdAndDate(long id, String date);
}
