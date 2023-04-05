package com.moviereservation.repository;

import com.moviereservation.domain.Category;
import com.moviereservation.domain.JoinedMovieDescription;
import com.moviereservation.domain.Movie;
import com.moviereservation.domain.MovieDescription;
import com.moviereservation.rowmapper.CategoryMapper;
import com.moviereservation.rowmapper.MovieDescriptionMapper;
import com.moviereservation.rowmapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public JoinedMovieDescription description(Long id){
        String description_sql = "select * from movie_descriptions where seq = ?";
        String movie_sql = "select * from movies where seq = ?";
        String category_sql = "select * from categories where seq = ?";
        MovieDescription movieDescription = jdbcTemplate.queryForObject(description_sql, new MovieDescriptionMapper(), id);
        Movie movie = jdbcTemplate.queryForObject(movie_sql, new MovieMapper(), movieDescription.getMoviesSeq());
        Category category = jdbcTemplate.queryForObject(category_sql, new CategoryMapper(), movieDescription.getCategorySeq());
        return new JoinedMovieDescription(movieDescription, movie, category);
    }
}
