package com.moviereservation.domain.movie;

import com.moviereservation.domain.movie.comment.repository.CommentMemberRepository;
import com.moviereservation.domain.movie.description.repository.MovieDescriptionRepository;
import com.moviereservation.domain.movie.dto.DetailDto;
import com.moviereservation.domain.movie.repository.MovieRepository;
import com.moviereservation.domain.movie.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    CommentMemberRepository commentMemberRepository;

    @Mock
    MovieDescriptionRepository movieDescriptionRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMovieTest(){
        int serviceReturn = movieService.getAllMovies().size();
        int repositoryReturn = movieRepository.getAllMovies().size();

        assertThat(serviceReturn).isEqualTo(repositoryReturn);
    }

    @Test
    void findByMovieIdTest(){

        DetailDto detailDto = movieService.findByMovieId(13L);

        assertThat(detailDto).isNull();
    }

}
