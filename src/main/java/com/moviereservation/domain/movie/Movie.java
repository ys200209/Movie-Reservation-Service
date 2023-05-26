package com.moviereservation.domain.movie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Movie {
    private final Long seq;
    private final String movieName;
    private final String poster;
}
