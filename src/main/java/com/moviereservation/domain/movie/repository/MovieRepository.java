package com.moviereservation.domain.movie.repository;

import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import com.moviereservation.domain.movie.dto.MoviePreviewDto;

import java.util.List;

public interface MovieRepository {
    public long save(MovieRegisterDto data);
    List<MoviePreviewDto> getAllMovies();
}
