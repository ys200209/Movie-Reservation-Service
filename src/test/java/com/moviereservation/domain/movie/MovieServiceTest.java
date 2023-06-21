package com.moviereservation.domain.movie;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.moviereservation.domain.comment.CommentMemberRepository;
import com.moviereservation.service.movie.MovieService;
import com.moviereservation.web.movie.dto.DetailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieServiceTest {
    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    CommentMemberRepository commentMemberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMovieTest() {
        int serviceReturn = movieService.getAllMovies().size();
        int repositoryReturn = movieRepository.getAllMovies().size();

        assertThat(serviceReturn).isEqualTo(repositoryReturn);
    }

    @Test
    void findByMovieIdTest() {

        DetailDto detailDto = movieService.findByMovieId(13L);

        assertThat(detailDto).isNull();
    }

}
