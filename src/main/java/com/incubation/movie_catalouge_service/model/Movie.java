package com.incubation.movie_catalouge_service.model;

public class Movie {


    private String movieId;
    private String movieName;
    private String movieDescription;


    public Movie(String movieId, String movieName, String movieDescription) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
    }


    public String getMovieId() {
        return movieId;
    }
    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }
}
