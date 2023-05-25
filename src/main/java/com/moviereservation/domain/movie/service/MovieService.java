package com.moviereservation.domain.movie.service;

import com.moviereservation.domain.movie.comment.dto.AddCommentDto;
import com.moviereservation.domain.movie.comment.domain.CommentMember;
import com.moviereservation.domain.movie.comment.dto.CommentMemberDto;
import com.moviereservation.domain.movie.comment.repository.CommentMemberRepository;
import com.moviereservation.domain.movie.description.domain.MovieDescription;
import com.moviereservation.domain.movie.description.dto.MovieDescriptionDto;
import com.moviereservation.domain.movie.description.repository.MovieDescriptionRepository;
import com.moviereservation.domain.movie.dto.MoviePreviewDto;
import com.moviereservation.domain.movie.repository.MovieRepository;
import com.moviereservation.domain.movie.domain.Detail;
import com.moviereservation.domain.movie.dto.DetailDto;
import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieDescriptionRepository movieDescriptionRepository;
    private final CommentMemberRepository commentMemberRepository;
    private final MovieRepository movieRepository;

    public List<MoviePreviewDto> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public DetailDto findByMovieId(Long id) {
        MovieDescriptionDto movieDescriptionDto = movieDescriptionRepository.findByMovieId(id);
        List<CommentMember> comments = commentMemberRepository.findByMovieId(id);
        Detail detail = new Detail(movieDescriptionDto, comments);
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

    public void addComment(AddCommentDto dto, Long moviesSeq){
        String memberId = dto.getMemberId();
        String content = dto.getContent();
        content = XSSProtect(content);
        commentMemberRepository.addComment(memberId, content, moviesSeq);
    }

    public void saveMovie(MovieRegisterDto data){

        String ProtectName = XSSProtect(data.getName());
        String ProtectStory = XSSProtect(data.getStory());
        String ProtectDirector = XSSProtect(data.getDirector());
        String ProtectActor = XSSProtect(data.getActor());
        MovieRegisterDto XSSProtectingData = new MovieRegisterDto(
                ProtectName, data.getPoster(), data.getCategory(), ProtectStory, data.getRunningTime(), ProtectDirector, ProtectActor, data.getAgeLimit());
        long movieSeq = movieRepository.save(XSSProtectingData);
        movieDescriptionRepository.save(XSSProtectingData, movieSeq, Long.parseLong(XSSProtectingData.getCategory()));

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

    private String XSSProtect(String s){
        return s.replaceAll("\\<", "&lt;").replaceAll("\\>", "&gt;").replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
    }
}
