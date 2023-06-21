package com.moviereservation.domain.movie.service;

import com.moviereservation.domain.movie.comment.dto.AddCommentDto;
import com.moviereservation.domain.movie.domain.CommentMember;
import com.moviereservation.domain.movie.comment.dto.CommentMemberDto;
import com.moviereservation.domain.movie.comment.repository.CommentMemberRepository;
import com.moviereservation.domain.movie.domain.MovieDescription;
import com.moviereservation.domain.movie.description.dto.MovieDescriptionDto;
import com.moviereservation.domain.movie.description.repository.MovieDescriptionRepository;
import com.moviereservation.domain.movie.domain.Movie;
import com.moviereservation.domain.movie.dto.MoviePreviewDto;
import com.moviereservation.domain.movie.repository.MovieRepository;
import com.moviereservation.domain.movie.domain.Detail;
import com.moviereservation.domain.movie.dto.DetailDto;
import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import com.moviereservation.util.XssProtector;
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
        List<MoviePreviewDto> movieDtoList = new ArrayList<MoviePreviewDto>();
        List<Movie> movies = movieRepository.getAllMovies();
        for (Movie movie : movies) {
            movieDtoList.add(new MoviePreviewDto(movie.getSeq(), movie.getMovieName(), movie.getPoster()));
        }

        return movieDtoList;
    }

    public DetailDto findByMovieId(Long id) {
        MovieDescriptionDto movieDescriptionDto = movieDescriptionRepository.findByMovieId(id);
        if(movieDescriptionDto == null){
            return null;
        }
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
        content = XssProtector.xssProtecting(content);
        commentMemberRepository.addComment(memberId, content, moviesSeq);
    }

    public void saveMovie(MovieRegisterDto data){

        String ProtectName = XssProtector.xssProtecting(data.getName());
        String ProtectStory = XssProtector.xssProtecting(data.getStory());
        String ProtectDirector = XssProtector.xssProtecting(data.getDirector());
        String ProtectActor = XssProtector.xssProtecting(data.getActor());
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

}
