package com.moviereservation.domain.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MoviePreviewDto> getAllMovies() {
        return movieRepository.getAllMovies();
    }
}
