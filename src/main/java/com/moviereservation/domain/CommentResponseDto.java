package com.moviereservation.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private final Long seq;
    private final String content;
    private final String name;
    private final Date createAt;
}
