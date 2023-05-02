package com.moviereservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Movie {
    private final Long seq;
    private final String movie_name;
    private final String poster;
}
