package com.moviereservation.study.domain;

import com.moviereservation.utils.exception.ReservationNotAllowedException;
import java.util.Arrays;

public enum SeatStatus {
    NORMAL("normal"),
    ACTIVE("active"),
    DISABLED("disabled"),
    EMPTY("empty"),
    ;

    public static final String STATUS_NOT_FOUND_MESSAGE = "예약 상태를 찾을 수 없는 좌석입니다.";
    private final String status;

    SeatStatus(String status) {
        this.status = status;
    }

    public static SeatStatus of(String statusText) {
        return Arrays.stream(values())
                .filter(seatStatus -> seatStatus.status.equals(statusText))
                .findFirst()
                .orElseThrow(() -> new ReservationNotAllowedException(STATUS_NOT_FOUND_MESSAGE));
    }

    public boolean cannotReserve() {
        return this != SeatStatus.NORMAL;
    }

    public String getStatus() {
        return status;
    }
}
