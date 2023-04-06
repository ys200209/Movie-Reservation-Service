package com.moviereservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieDescription {
    private final Long moviesSeq;
    private final Long categorySeq;
    private final String story;
    private final int runningTime;
    private final String director;
    private final String actor;
    private final int ageLimit;



}
