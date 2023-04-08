package com.moviereservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JoinedMovieDescription {
    private final MovieDescription movieDescription;
    private final Movie movie;
    private final Category category;
}
