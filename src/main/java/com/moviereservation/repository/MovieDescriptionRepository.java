package com.moviereservation.repository;

import com.moviereservation.domain.CommentResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDescriptionRepository {
    public List<CommentResponseDto> getCommentsByMoviesSeq(Long moviesSeq);
}
