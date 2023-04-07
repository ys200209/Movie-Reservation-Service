package com.moviereservation.service;

import com.moviereservation.domain.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<CommentResponseDto> getCommentByMoviesSeq(Long moviesSeq);
}
