package com.moviereservation.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class MovieDescription {
    @Id
    private Long seq;
    private Long moviesSeq;
    private Long categorySeq;
    private String story;
    private int runningTime;
    private String director;
    private String actor;
    private int ageLimit;

    public MovieDescription(Long movies_seq, Long category_seq, String story, int running_time, String director, String actor, int age_limit) {
        this.moviesSeq = movies_seq;
        this.categorySeq = category_seq;
        this.story = story;
        this.runningTime = running_time;
        this.director = director;
        this.actor = actor;
        this.ageLimit = age_limit;
    }
}
