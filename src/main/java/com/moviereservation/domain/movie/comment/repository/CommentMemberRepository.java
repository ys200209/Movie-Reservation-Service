package com.moviereservation.domain.movie.comment.repository;

import com.moviereservation.domain.movie.comment.domain.CommentMember;

import java.util.List;

public interface CommentMemberRepository {
    List<CommentMember> findByMovieId(Long id);
    void addComment(String memberId, String content, Long moviesSeq);
}
