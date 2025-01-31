package com.incubation.movie_catalouge_service.model;

public class Rating {
    private String movieId;
    private Integer rating;

    public Rating(String movieId, Integer rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public Integer getRating() {
        return rating;
    }
}
