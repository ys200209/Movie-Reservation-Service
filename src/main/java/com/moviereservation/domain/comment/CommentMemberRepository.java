package com.moviereservation.domain.comment;

import com.moviereservation.domain.comment.dto.CommentMember;

import java.util.List;

public interface CommentMemberRepository {
    List<CommentMember> findByMovieId(Long id);
    void addComment(String memberId, String content, Long moviesSeq);
}
