package com.incubation.movie_catalouge_service.client;

import com.incubation.movie_catalouge_service.model.CatlogItems;
import com.incubation.movie_catalouge_service.model.Movie;
import com.incubation.movie_catalouge_service.model.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MovieClient {

    private static final String MOVIE_CATALOG_SERVICE = "movieCatalogService";
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = MOVIE_CATALOG_SERVICE, fallbackMethod = "getMovieInfoFallback")
    public List<CatlogItems> getCatalogItems(UserRating ratings) {
        return ratings.getRatings().stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatlogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
                }
        ).collect(Collectors.toList());
    }

    public List<CatlogItems> getMovieInfoFallback(UserRating ratings) {
        return new ArrayList<CatlogItems>(Collections.singletonList(new CatlogItems("FallBackMovie", "FallBackCalled", 0)));
    }
}
