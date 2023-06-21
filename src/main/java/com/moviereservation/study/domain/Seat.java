package com.moviereservation.study.domain;

import com.moviereservation.domain.member.Gender;
import com.moviereservation.domain.member.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.jdbc.core.RowMapper;

@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
@ToString
public class Seat {
    private final SeatStatus status;
    private final int row;
    private final int column;

    public static final RowMapper<Seat> seatMapper = (rs, rowNum) -> {
        return Seat.builder()
                .status(SeatStatus.of(rs.getString("status")))
                .row(rs.getInt("seat_row"))
                .column(rs.getInt("seat_column"))
                .build();
    };
}
