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
        DetailDto detailDto = new DetailDto(
                movieDescription.getMovieName(),
                movieDescription.getPoster(),
                movieDescription.getCategoryName(),
                movieDescription.getStory(),
                movieDescription.getRunningTime(),
                movieDescription.getDirector(),
                movieDescription.getActor(),
                movieDescription.getAgeLimit(),
                comments
        );
        return detailDto;
    }
}
