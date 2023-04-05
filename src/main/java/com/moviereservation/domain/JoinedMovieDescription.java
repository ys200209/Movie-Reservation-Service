package com.moviereservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinedMovieDescription {
    private MovieDescription movieDescription;
    private Movie movie;
    private Category category;
}
