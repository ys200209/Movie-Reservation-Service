package com.moviereservation.domain.detail.comment;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class CommentMemberDto {
    private final Long commentSeq;
    private final String memberName;
    private final String content;
    private final Date lastCreateAt;
    private final Boolean modified;

    public CommentMemberDto(CommentMember commentMember){
        this.commentSeq = commentMember.getCommentSeq();
        this.memberName = commentMember.getMemberName();
        this.content = commentMember.getContent();
        if(commentMember.getCreateAt().equals(commentMember.getModifyAt())){
            this.lastCreateAt = commentMember.getCreateAt();
            this.modified = false;
        }else{
            this.lastCreateAt = commentMember.getModifyAt();
            this.modified = true;
        }
    }

}
