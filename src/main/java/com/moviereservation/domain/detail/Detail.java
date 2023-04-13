package com.moviereservation.domain.detail;

import com.moviereservation.domain.detail.comment.CommentMember;
import com.moviereservation.domain.detail.description.MovieDescription;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Detail {
    private final MovieDescription movieDescription;
    private final List<CommentMember> comments;
}
