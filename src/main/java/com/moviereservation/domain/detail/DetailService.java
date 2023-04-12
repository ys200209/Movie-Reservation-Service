package com.moviereservation.domain.detail;

import com.moviereservation.domain.detail.comment.CommentMember;
import com.moviereservation.domain.detail.comment.CommentMemberDto;
import com.moviereservation.domain.detail.comment.CommentMemberRepository;
import com.moviereservation.domain.detail.description.MovieDescription;
import com.moviereservation.domain.detail.description.MovieDescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final MovieDescriptionRepository movieDescriptionRepository;
    private final CommentMemberRepository commentMemberRepository;

    public DetailDto findByMovieId(Long id) {
        MovieDescription movieDescription = movieDescriptionRepository.findByMovieId(id);
        List<CommentMember> comments = commentMemberRepository.findByMovieId(id);
        Detail detail = new Detail(movieDescription, comments);
        List<CommentMemberDto> commentsDto = this.mapping(detail.getComments());
        DetailDto detailDto = new DetailDto(
                detail.getMovieDescription().getMovieName(),
                detail.getMovieDescription().getPoster(),
                detail.getMovieDescription().getCategoryName(),
                detail.getMovieDescription().getStory(),
                detail.getMovieDescription().getRunningTime(),
                detail.getMovieDescription().getDirector(),
                detail.getMovieDescription().getActor(),
                detail.getMovieDescription().getAgeLimit(),
                commentsDto
        );
        return detailDto;
    }

    public List<CommentMemberDto> mapping(List<CommentMember> comments){
        List<CommentMemberDto> mappedComments = new ArrayList<>();
        CommentMember commentMember;
        CommentMemberDto commentMemberDto;
        for(int i = 0; i < comments.size(); i++){
            commentMember = comments.get(i);
            commentMemberDto = new CommentMemberDto(commentMember);
            mappedComments.add(commentMemberDto);
        }
        return mappedComments;
    }
}
