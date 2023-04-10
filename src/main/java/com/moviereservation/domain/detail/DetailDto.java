package com.moviereservation.domain.detail;

import com.moviereservation.domain.detail.comment.CommentMember;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DetailDto {
    private final String movieName;
    private final String poster;
    private final String categoryName;
    private final String story;
    private final int runningTime;
    private final String director;
    private final String actor;
    private final int ageLimit;
    private final List<CommentMember> comments;
}
