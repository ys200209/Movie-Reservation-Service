package com.moviereservation.domain.reservation;

import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.Seats;

import java.util.List;

public interface ReservationRepository {
    int reserve(long scheduleId, Seats seats);

    Seat findSeat(long scheduleId, Seat seat);

    List<Seat> findByScheduleIdAndColumn(long id, int col);

    void updatePayment(Seat seat, String grade);
}
