package com.moviereservation.service;

import com.moviereservation.domain.CommentResponseDto;
import com.moviereservation.repository.MovieDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private MovieDescriptionRepository movieDescriptionRepository;

    @Override
    public List<CommentResponseDto> getCommentByMoviesSeq(Long moviesSeq) {
        List<CommentResponseDto> commentResponseDto = movieDescriptionRepository.getCommentsByMoviesSeq(moviesSeq);
        return commentResponseDto;
    }
}
