package com.moviereservation.service;

import com.moviereservation.domain.JoinedMovieDescription;

public interface MovieService {
    public JoinedMovieDescription findById(Long seq);
}
