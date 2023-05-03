package com.moviereservation.domain.main;

import java.util.List;

public interface MovieRepository {
    List<MoviePreviewDto> getAllMovies();
}
