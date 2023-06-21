package com.moviereservation.domain.movie.domain;

import com.moviereservation.domain.movie.description.dto.MovieDescriptionDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Detail {
    private final MovieDescriptionDto movieDescription;
    private final List<CommentMember> comments;
}
