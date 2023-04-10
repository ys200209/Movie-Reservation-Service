package com.moviereservation.domain.detail.comment;

import java.util.List;

public interface CommentMemberRepository {
    List<CommentMember> findByMovieId(Long seq);
}
