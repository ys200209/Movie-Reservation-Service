package com.moviereservation.domain.movie.reservation.controller.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReservationRequestDtoTest {

    @Test
    void testCreate() {
        // given
        ReservationRequestDto reservationDto = new ReservationRequestDto("test");

        // then
        assertThat(reservationDto.getSelected()).isEqualTo("test");
    }
}
