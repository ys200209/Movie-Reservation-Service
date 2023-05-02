package com.moviereservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Movie {
    private final Long seq;
    private final String movie_name;
    private final String poster;
}
