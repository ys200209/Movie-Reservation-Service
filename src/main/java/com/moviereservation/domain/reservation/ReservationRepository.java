package com.moviereservation.domain.reservation;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.Seats;

import java.util.List;

public interface ReservationRepository {
    int reserve(long scheduleId, Seats seats);

    Seat findSeat(long scheduleId, Seat seat);

    List<Seat> findByScheduleIdAndColumn(long id, int col);

    long insertPayment(String age, long movies_seq, Member member);

    void save(long seat_seq, long payment_seq);

    long findSeatSql(Seat seat, long scheduleId);
}
