package com.moviereservation.domain.seat;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Theater {
    private final List<Seats> theater;
}
