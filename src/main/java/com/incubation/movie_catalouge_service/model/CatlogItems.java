package com.incubation.movie_catalouge_service.model;

public class CatlogItems {
    private String movieName;
    private String movieDescription;
    private Integer rating;

    public CatlogItems(String movieName,String movieDescription,Integer rating){
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public Integer getRating() {
        return rating;
    }
}
