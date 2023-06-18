package com.moviereservation.domain.movie.description.repository;

import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import com.moviereservation.domain.movie.description.dto.MovieDescriptionDto;

public interface MovieDescriptionRepository {
    MovieDescriptionDto findByMovieId(Long id);
    void save(MovieRegisterDto data, long movies_seq, long categories_seq);
}
