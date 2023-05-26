package com.moviereservation.domain.movie.description.dto;

import com.moviereservation.domain.movie.description.MovieDescription;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieDescriptionDto {
    private final String movieName;
    private final String poster;
    private final String categoryName;
    private final String story;
    private final int runningTime;
    private final String director;
    private final String actor;
    private final int ageLimit;

    public MovieDescriptionDto(MovieDescription domain){
        movieName = domain.getMovieName();
        poster = domain.getPoster();
        categoryName = domain.getCategoryName();
        story = domain.getStory();
        runningTime = domain.getRunningTime();
        director = domain.getDirector();
        actor = domain.getActor();
        ageLimit = domain.getAgeLimit();
    }
}
