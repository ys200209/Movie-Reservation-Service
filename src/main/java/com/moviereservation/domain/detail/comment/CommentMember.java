package com.moviereservation.domain.detail.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CommentMember {
    private final Long cSeq;
    private final String mName;
    private final Long moviesSeq;
    private final String content;
    private final Date createAt;
    private final Date modifyAt;
}
