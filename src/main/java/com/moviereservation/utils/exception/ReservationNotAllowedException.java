package com.moviereservation.utils.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReservationNotAllowedException extends RuntimeException {
    private final String message;
}
