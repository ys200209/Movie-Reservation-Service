package com.moviereservation.web.movie.dto;

import com.moviereservation.domain.comment.dto.CommentMemberDto;
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
    private final List<CommentMemberDto> comments;
}
