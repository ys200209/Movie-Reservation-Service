package com.moviereservation.domain.movie.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AddCommentDto {
    private final String memberId;
    private final String content;
}
