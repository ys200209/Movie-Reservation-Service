package com.moviereservation.domain.movie.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CommentMember {
    private final Long commentSeq;
    private final String memberName;
    private final Long moviesSeq;
    private final String content;
    private final Timestamp createAt;
    private final Timestamp modifyAt;
}
