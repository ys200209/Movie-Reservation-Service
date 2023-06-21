package com.moviereservation.domain.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
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
