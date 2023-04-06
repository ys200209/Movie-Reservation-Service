package com.moviereservation.repository;


import com.moviereservation.domain.JoinedMovieDescription;
import com.moviereservation.domain.MovieDescription;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository {
    public JoinedMovieDescription description(Long seq);
}
