package com.moviereservation.repository;

import com.moviereservation.domain.Category;
import com.moviereservation.domain.JoinedMovieDescription;
import com.moviereservation.domain.Movie;
import com.moviereservation.domain.MovieDescription;
import com.moviereservation.rowmapper.CategoryMapper;
import com.moviereservation.rowmapper.MovieDescriptionMapper;
import com.moviereservation.rowmapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository{

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Override
    public JoinedMovieDescription description(Long seq){
        final String description_sql = "select * from movie_descriptions where seq = ?";
        final String movie_sql = "select * from movies where seq = ?";
        final String category_sql = "select * from categories where seq = ?";
        MovieDescription movieDescription = jdbcTemplate.queryForObject(description_sql, new MovieDescriptionMapper(), seq);
        Movie movie = jdbcTemplate.queryForObject(movie_sql, new MovieMapper(), movieDescription.getMoviesSeq());
        Category category = jdbcTemplate.queryForObject(category_sql, new CategoryMapper(), movieDescription.getCategorySeq());
        return new JoinedMovieDescription(movieDescription, movie, category);
    }
}
