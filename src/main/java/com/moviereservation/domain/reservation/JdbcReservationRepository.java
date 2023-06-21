package com.moviereservation.domain.reservation;

import com.moviereservation.domain.member.Member;
import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.SeatStatus;
import com.moviereservation.domain.seat.Seats;

import com.moviereservation.utils.strategy.DiscountPolicy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.moviereservation.utils.reservation.AdultCalc;
import com.moviereservation.utils.reservation.AgeCalc;
import com.moviereservation.utils.reservation.ChildCalc;
import com.moviereservation.utils.reservation.TeenagerCalc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcReservationRepository implements ReservationRepository {
    public static final String SELECT_SEAT_QUERY = "SELECT * FROM Seats WHERE schedules_seq = ? AND seat_row = ? AND seat_column = ?";
    public static final String UPDATE_SEAT_RESERVATION_QUERY = "UPDATE Seats SET status = ? WHERE schedules_seq = ? AND seat_row = ? AND seat_column = ?";
    private static final String SELECT_SEAT_BY_ROW_QUERY = "SELECT * FROM SEATS WHERE SCHEDULES_SEQ = ? AND SEAT_ROW = ?";
    private static final String INSERT_PAYMENT_SQL = "INSERT INTO PAYMENTS(MOVIES_SEQ, MEMBERS_SEQ, CARD_NUMBER, COUNT_CHILD, COUNT_TEENAGER, COUNT_ADULT, PAY_AMOUNT, PAYMENT_AT) VALUES(?, ?, '1111', ?, ?, ?, ?, ?)";
    private static final String INSERT_ENROLLMENT_SQL = "INSERT INTO ENROLLMENT_SEATS(PAYMENTS_SEQ, SEATS_SEQ) VALUES(?, ?)";
    private static final String SELECT_SEAT_SEQ_QUERY = "SELECT SEQ FROM SEATS WHERE SCHEDULES_SEQ = ? AND SEAT_ROW = ? AND SEAT_COLUMN = ?";
    public static final String NOT_ALLOWED_COLUMN_MESSAGE = "예약 불가능한 좌석 열입니다.";
    public static final String SEAT_NOT_EXISTS_MESSAGE = "좌석이 존재하지 않습니다.";
    public static final String ALREADY_RESERVED_MESSAGE = "{1}행 {2}열은 이미 예약된 좌석입니다.";
    public  static final List<Integer> unreservableSeatColumns = List.of(3, 7);

    private final JdbcTemplate jdbcTemplate;
    private final DiscountPolicy discountPolicy;

    @Override
    public int reserve(long scheduleId, Seats seats) {
        int result = 0;
        for (Seat seat : seats.getSeats()) {
            result += jdbcTemplate.update(UPDATE_SEAT_RESERVATION_QUERY,
                    SeatStatus.DISABLED.getStatus(), scheduleId, seat.getRow(), seat.getColumn());
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

    @Override
    public List<Seat> findByScheduleIdAndColumn(long id, int row) {
        List<Seat> seats = jdbcTemplate.query(SELECT_SEAT_BY_ROW_QUERY, Seat.seatMapper, id, row);
        return seats;
    }

    //임시
    @Override
    public long insertPayment(String age, long movies_seq, Member member) {
            AgeCalc ageCalc;
            switch (age){
                case "child":
                    ageCalc = new ChildCalc();
                    break;
                case "teenager":
                    ageCalc = new TeenagerCalc();
                    break;
                case "adult":
                    ageCalc = new AdultCalc();
                    break;
                default:
                    ageCalc = new ChildCalc();
            }

            int[] updateValues = ageCalc.getUpdatedValues();

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update((Connection con) -> {
                PreparedStatement preparedStatement = con.prepareStatement(
                        INSERT_PAYMENT_SQL, new String[] {"seq"}
                );
                preparedStatement.setLong(1, movies_seq);
                preparedStatement.setLong(2, member.getSeq());
                preparedStatement.setInt(3, updateValues[0]);
                preparedStatement.setInt(4, updateValues[1]);
                preparedStatement.setInt(5, updateValues[2]);
                preparedStatement.setInt(6, getPayAmount(member, updateValues[3]));
                preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                return  preparedStatement;
            }, keyHolder);
            Number keyValue = keyHolder.getKey();
            return keyValue.longValue();
    }

    private int getPayAmount(Member member, int payAmount) {
        int discount = discountPolicy.discount(member, payAmount);
        return payAmount - discount;
    }

    @Override
    public long findSeatSql(Seat seat, long scheduleId) {
        return jdbcTemplate.queryForObject(SELECT_SEAT_SEQ_QUERY, Long.class, scheduleId, seat.getRow(), seat.getColumn());
    }

    @Override
    public void save(long seat_seq, long payment_seq) {
        jdbcTemplate.update(INSERT_ENROLLMENT_SQL, payment_seq, seat_seq);
    }
}
