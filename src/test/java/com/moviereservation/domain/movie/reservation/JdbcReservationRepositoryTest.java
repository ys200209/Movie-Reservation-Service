package com.moviereservation.domain.movie.reservation;

import static com.moviereservation.study.domain.SeatStatus.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.moviereservation.domain.movie.reservation.JdbcReservationRepository;
import com.moviereservation.domain.movie.reservation.utils.SeatsSeparator;
import com.moviereservation.domain.movie.reservation.controller.dto.ReservationRequestDto;
import com.moviereservation.study.domain.Seat;
import com.moviereservation.study.domain.Seats;
import com.moviereservation.utils.exception.ReservationNotAllowedException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@DataJdbcTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JdbcReservationRepositoryTest {

    JdbcReservationRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        repository = new JdbcReservationRepository(jdbcTemplate);
    }

    @ParameterizedTest
    @CsvSource(value = {"seat=2-8,1", "seat=2-8&seat=2-9&seat=3-8&seat=3-9,4"})
    void testReserveMovie_Success(String selected, int expected) {
        // given
        ReservationRequestDto reservationDto = new ReservationRequestDto(
                selected);

        // when
        Seats seats = SeatsSeparator.separate(reservationDto.getSelected());

        // then
        assertThat(repository.reserve(1L, seats)).isEqualTo(expected);
    }

    @Test
    void testFindSeat_Success() {
        // given
        Seat seat = new Seat(NORMAL, 5, 8);

        // when
        Seat findSeat = repository.findSeat(1L, seat);

        // then
        assertThat(findSeat).isEqualTo(seat);
    }

    @Test
    void testFindSeat_Exception() {
        // given
        Seat seat = new Seat(NORMAL, 6, 8);

        // then
        assertThatThrownBy(() -> repository.findSeat(1L, seat))
                .isInstanceOf(DataAccessException.class);
    }
}
