package com.moviereservation.domain.detail;

import com.moviereservation.domain.detail.comment.CommentMember;
import com.moviereservation.domain.detail.comment.CommentMemberRepository;
import com.moviereservation.domain.detail.description.MovieDescription;
import com.moviereservation.domain.detail.description.MovieDescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final MovieDescriptionRepository movieDescriptionRepository;
    private final CommentMemberRepository commentMemberRepository;

    @Override
    public DetailDto findByMovieId(Long seq) {
        MovieDescription movieDescription = movieDescriptionRepository.findByMovieId(seq);
        List<CommentMember> comments = commentMemberRepository.findByMovieId(seq);
        Detail detail = new Detail(movieDescription, comments);
        DetailDto detailDto = new DetailDto(
                detail.getMovieDescription().getMovieName(),
                detail.getMovieDescription().getPoster(),
                detail.getMovieDescription().getCategoryName(),
                detail.getMovieDescription().getStory(),
                detail.getMovieDescription().getRunningTime(),
                detail.getMovieDescription().getDirector(),
                detail.getMovieDescription().getActor(),
                detail.getMovieDescription().getAgeLimit(),
                detail.getComments()
        );
        return detailDto;
    }
}
