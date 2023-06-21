package com.moviereservation.domain.seat;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
@ToString
public class Seats {
    private final List<Seat> seats;
}
