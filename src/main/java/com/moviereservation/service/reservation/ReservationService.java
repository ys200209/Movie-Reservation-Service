package com.moviereservation.service.reservation;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.member.MemberRepository;
import com.moviereservation.domain.reservation.ReservationRepository;
import com.moviereservation.domain.schedule.ScheduleRepository;
import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.Seats;
import com.moviereservation.domain.seat.Theater;
import com.moviereservation.utils.reservation.SeatsSeparator;
import com.moviereservation.utils.exception.ReservationNotAllowedException;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.moviereservation.domain.reservation.JdbcReservationRepository.*;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Async
    @Synchronized
    public void testSyncMethod() {

    }


    public void reserve(long scheduleId, String selected, String age, String member_id) {
        Seats seats = SeatsSeparator.separate(selected);
        for(Seat seat : seats.getSeats()) {
            checkNotAllowedReservation(scheduleId, seat);
        }
        reservationRepository.reserve(scheduleId, seats);
        long movies_seq = scheduleRepository.findBySeq(scheduleId).get(0).getMovies_seq();
        Optional<Member> member_check = memberRepository.findById(member_id);
        if(member_check.get() == null){
            return;
        }
        Member member = member_check.get();
        Seat seat = seats.getSeats().get(0);
        long seatSeq = reservationRepository.findSeatSql(seat, scheduleId);
        long payments_seq = reservationRepository.insertPayment(age, movies_seq, member);
        reservationRepository.save(seatSeq, payments_seq);
    }

    public Theater getAllSeats(long scheduleId){
        Theater theater = new Theater(new ArrayList<>());
        for(int i = 1; i<7; i++){
            theater.getTheater().add(new Seats(reservationRepository.findByScheduleIdAndColumn(scheduleId, i)));
        }
        return theater;
    }

    public void checkNotAllowedReservation(long scheduleId, Seat seat) {
        checkNotAllowedColumn(seat);
        checkAlreadyReserved(scheduleId, seat);
    }

    private void checkNotAllowedColumn(Seat seat) {
        if (unreservableSeatColumns.contains(seat.getColumn())) {
            throw new ReservationNotAllowedException(NOT_ALLOWED_COLUMN_MESSAGE);
        }
    }

    private void checkAlreadyReserved(long scheduleId, Seat seat) {
        Seat findSeat = reservationRepository.findSeat(scheduleId, seat);

        if (findSeat == null) {
            throw new ReservationNotAllowedException(SEAT_NOT_EXISTS_MESSAGE);
        }

        if (findSeat.getStatus().cannotReserve()) {
            // findSeat.getRow() + "행 " + findSeat.getColumn() + "열은 이미 예약된 좌석입니다."
            throw new ReservationNotAllowedException(String.format(ALREADY_RESERVED_MESSAGE, findSeat.getRow(),
                    findSeat.getColumn()));
        }
    }
}
