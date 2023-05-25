package com.moviereservation.domain.movie.description.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieDescription {
    private final String movieName;
    private final String poster;
    private final String categoryName;
    private final String story;
    private final int runningTime;
    private final String director;
    private final String actor;
    private final int ageLimit;
}
