package com.moviereservation.service.movie;

import com.moviereservation.domain.comment.CommentMemberRepository;
import com.moviereservation.domain.comment.dto.AddCommentDto;
import com.moviereservation.domain.comment.dto.CommentMember;
import com.moviereservation.domain.comment.dto.CommentMemberDto;
import com.moviereservation.domain.movie.Detail;
import com.moviereservation.domain.movie.Movie;
import com.moviereservation.domain.movie.MovieRepository;
import com.moviereservation.utils.xssprotector.XssProtector;
import com.moviereservation.web.movie.dto.DetailDto;
import com.moviereservation.web.movie.dto.MovieDescriptionDto;
import com.moviereservation.web.movie.dto.MoviePreviewDto;
import com.moviereservation.web.movie.dto.MovieRegisterDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
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
        MovieDescriptionDto movieDescriptionDto = movieRepository.findByMovieId(id);
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
        movieRepository.save(XSSProtectingData, movieSeq, Long.parseLong(XSSProtectingData.getCategory()));

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
