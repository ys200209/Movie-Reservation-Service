package com.moviereservation.domain.detail.description;

public interface MovieDescriptionRepository {
    MovieDescription findByMovie(Long seq);
}
