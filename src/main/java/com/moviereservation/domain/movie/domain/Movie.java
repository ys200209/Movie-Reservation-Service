package com.moviereservation.domain.movie.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Movie {
    private final Long seq;
    private final String movieName;
    private final String poster;
}
