package com.moviereservation.domain.movie.reservation;

import com.moviereservation.study.domain.Seat;
import com.moviereservation.study.domain.Seats;

public interface ReservationRepository {
    int reserve(long scheduleId, Seats seats);

    Seat findSeat(long scheduleId, Seat seat);
}
