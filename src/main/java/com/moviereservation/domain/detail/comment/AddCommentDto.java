package com.moviereservation.domain.detail.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AddCommentDto {
    private final String memberId;
    private final String content;
}
