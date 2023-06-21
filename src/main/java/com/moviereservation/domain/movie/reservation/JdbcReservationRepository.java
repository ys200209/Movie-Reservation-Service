package com.moviereservation.domain.movie.reservation;

import com.moviereservation.study.domain.Seat;
import com.moviereservation.study.domain.SeatStatus;
import com.moviereservation.study.domain.Seats;
import com.moviereservation.utils.exception.ReservationNotAllowedException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcReservationRepository implements ReservationRepository {
    public static final String SELECT_SEAT_QUERY = "SELECT * FROM Seats WHERE schedules_seq = ? AND seat_row = ? AND seat_column = ?";
    public static final String UPDATE_SEAT_RESERVATION_QUERY = "UPDATE Seats SET status = ? WHERE schedules_seq = ? AND seat_row = ? AND seat_column = ?";
    public static final String NOT_ALLOWED_COLUMN_MESSAGE = "예약 불가능한 좌석 열입니다.";
    public static final String SEAT_NOT_EXISTS_MESSAGE = "좌석이 존재하지 않습니다.";
    public static final String ALREADY_RESERVED_MESSAGE = "{1}행 {2}열은 이미 예약된 좌석입니다.";
    public  static final List<Integer> unreservableSeatColumns = List.of(3, 7);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int reserve(long scheduleId, Seats seats) {
        int result = 0;
        for (Seat seat : seats.getSeats()) {
            result += jdbcTemplate.update(UPDATE_SEAT_RESERVATION_QUERY,
                    SeatStatus.ACTIVE.getStatus(), scheduleId, seat.getRow(), seat.getColumn());
        }
        return result;
    }

    /**
     * @throws org.springframework.dao.DataAccessException 찾으려는 좌석이 존재하지 않을 때 발생
     */
    @Override
    public Seat findSeat(long scheduleId, Seat seat) {
        return jdbcTemplate.queryForObject(
                SELECT_SEAT_QUERY,
                Seat.seatMapper, scheduleId, seat.getRow(), seat.getColumn());
    }
}
