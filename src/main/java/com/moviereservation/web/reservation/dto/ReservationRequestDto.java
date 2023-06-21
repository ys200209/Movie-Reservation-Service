package com.moviereservation.web.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@ToString
public class ReservationRequestDto {
    private final String selected;
    private final String memberId;
    private final String age;
}
