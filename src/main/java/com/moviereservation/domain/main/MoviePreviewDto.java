package com.moviereservation.domain.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MoviePreviewDto {
    private final Long seq;
    private final String movieName;
    private final String poster;
}
