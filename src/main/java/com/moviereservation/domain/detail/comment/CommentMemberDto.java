package com.moviereservation.domain.detail.comment;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class CommentMemberDto {
    private final Long commentSeq;
    private final String memberName;
    private final String content;
    private final LocalDateTime lastCreateAt;

    public CommentMemberDto(CommentMember commentMember){
        this.commentSeq = commentMember.getCommentSeq();
        this.memberName = commentMember.getMemberName();
        this.content = commentMember.getContent();
        this.lastCreateAt = commentMember.getModifyAt().toLocalDateTime();
    }
}
