package com.moviereservation.detail;

import com.moviereservation.domain.movie.repository.JdbcMovieRepository;
import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import com.moviereservation.domain.movie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddMovieTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MovieRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JdbcMovieRepository(jdbcTemplate);
    }

    @Test
    void testSaveMember() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/static/images/겨울왕국.png"));
        MultipartFile poster = new MockMultipartFile("겨울왕국", "겨울왕국.png", "png", fileInputStream);
        // given
        MovieRegisterDto movie = new MovieRegisterDto("a", poster, "c", "d", "1", "e", "f", "2");

        // when
        long actual = repository.save(movie);

        // then
        assertThat(actual).isEqualTo(13);
    }
}
