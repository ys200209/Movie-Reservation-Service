package com.moviereservation.domain.movie;

import com.moviereservation.web.movie.dto.MovieDescriptionDto;
import com.moviereservation.web.movie.dto.MovieRegisterDto;
import java.util.List;

public interface MovieRepository {
    MovieDescriptionDto findByMovieId(Long id);

    void save(MovieRegisterDto data, long movies_seq, long categories_seq);

    long save(MovieRegisterDto data);

    Movie findById(long id);

    List<Movie> getAllMovies();
}
