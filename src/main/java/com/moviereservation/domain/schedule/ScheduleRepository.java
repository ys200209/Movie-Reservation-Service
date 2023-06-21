package com.moviereservation.domain.schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findByMovieIdAndDate(long id, String date);
    List<Schedule> findBySeq(long seq);
}
