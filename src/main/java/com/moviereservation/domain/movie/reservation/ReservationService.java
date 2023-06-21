package com.moviereservation.domain.movie.reservation;

import com.moviereservation.study.domain.Seat;
import com.moviereservation.utils.exception.ReservationNotAllowedException;
import lombok.Synchronized;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Async
    @Synchronized
    public void testSyncMethod() {

    }


    /*public void reserve(long scheduleId, String selected) {

        checkNotAllowedReservation(scheduleId, seat);

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
        Seat findSeat = findSeat(scheduleId, seat);

        if (findSeat == null) {
            throw new ReservationNotAllowedException(SEAT_NOT_EXISTS_MESSAGE);
        }

        if (findSeat.getStatus().cannotReserve()) {
            // findSeat.getRow() + "행 " + findSeat.getColumn() + "열은 이미 예약된 좌석입니다."
            throw new ReservationNotAllowedException(String.format(ALREADY_RESERVED_MESSAGE, findSeat.getRow(),
                    findSeat.getColumn()));
        }
    }*/
}
