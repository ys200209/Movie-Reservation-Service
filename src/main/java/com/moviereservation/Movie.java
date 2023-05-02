package com.moviereservation;

public class Movie {
    private Long seq;
    private String movie_name;
    private String poster;

    public Movie(String movie_name, String poster){
        this.movie_name=movie_name;
        this.poster=poster;
    }
    public Long getSeq(){return seq;}

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
