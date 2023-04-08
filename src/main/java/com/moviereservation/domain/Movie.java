package com.moviereservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Movie {

    private final Long seq;
    private final String name;
    private final String poster;
}
