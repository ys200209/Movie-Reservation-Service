package com.moviereservation.domain.movie.reservation.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereservation.domain.movie.reservation.utils.SeatsSeparator;
import com.moviereservation.study.domain.Seat;
import com.moviereservation.study.domain.SeatStatus;
import com.moviereservation.study.domain.Seats;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SeatsSeparatorTest {

    @ParameterizedTest
    @MethodSource("getSelectedText")
    void testSeparateSeats(String selected, Seats expected) {
        // when
        Seats seats = SeatsSeparator.separate(selected);
        System.out.println("seats = " + seats);

        // then
        assertThat(seats).isEqualTo(expected);
    }

    public static Stream<Arguments> getSelectedText() {
        return Stream.of(
                Arguments.of("seat=2-8", new Seats(List.of(new Seat(SeatStatus.ACTIVE, 2, 8)))),
                Arguments.of("seat=2-8&seat=2-9&seat=3-8&seat=3-9",
                        new Seats(List.of(
                                new Seat(SeatStatus.ACTIVE, 2, 8),
                                new Seat(SeatStatus.ACTIVE, 2, 9),
                                new Seat(SeatStatus.ACTIVE, 3, 8),
                                new Seat(SeatStatus.ACTIVE, 3, 9))))
        );
    }
}
