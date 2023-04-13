package com.moviereservation.domain.detail.comment;

import java.util.List;

public interface CommentMemberRepository {
    List<CommentMember> findByMovieId(Long id);
    void addComment(String memberId, String content, Long moviesSeq);
}
