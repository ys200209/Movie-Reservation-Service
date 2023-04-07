package com.moviereservation.service;

import com.moviereservation.domain.JoinedMovieDescription;
import com.moviereservation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository movieRepository;

    @Override
    public JoinedMovieDescription findById(Long seq) {
        return movieRepository.description(seq);
    }
}
