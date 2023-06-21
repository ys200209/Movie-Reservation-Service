package com.moviereservation.domain.movie;

import com.moviereservation.domain.comment.dto.CommentMember;
import com.moviereservation.web.movie.dto.MovieDescriptionDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Detail {
    private final MovieDescriptionDto movieDescription;
    private final List<CommentMember> comments;
}
