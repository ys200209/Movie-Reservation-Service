package com.moviereservation.domain.reservation;

import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.Seats;

public interface ReservationRepository {
    int reserve(long scheduleId, Seats seats);

    Seat findSeat(long scheduleId, Seat seat);
}
