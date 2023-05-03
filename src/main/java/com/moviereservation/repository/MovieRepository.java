package com.moviereservation.repository;


import com.moviereservation.domain.JoinedMovieDescription;
import com.moviereservation.domain.MovieDescription;

public interface MovieRepository {
    public JoinedMovieDescription description(Long id);
}
