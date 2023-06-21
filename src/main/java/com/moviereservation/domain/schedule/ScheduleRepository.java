package com.moviereservation.domain.schedule;

import com.moviereservation.web.schedule.dto.AddScheduleDto;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findByMovieIdAndDate(long id, String date);
    List<Schedule> findBySeq(long seq);
    long save(AddScheduleDto dto);
    void createSeats(long scheduleSeq);
}
