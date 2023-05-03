package com.moviereservation.domain.detail.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCommentDto {
    private final String memberId;
    private final String content;
}
