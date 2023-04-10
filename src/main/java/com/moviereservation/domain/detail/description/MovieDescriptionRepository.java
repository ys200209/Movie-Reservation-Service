package com.moviereservation.domain.detail.description;

public interface MovieDescriptionRepository {
    MovieDescription findByMovieId(Long seq);
}
