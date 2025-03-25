package com.incubation.movie_catalouge_service.client;

import com.incubation.movie_catalouge_service.model.Rating;
import com.incubation.movie_catalouge_service.model.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class RatingsClient {
    private static final String MOVIE_CATALOG_SERVICE = "movieCatalogService";
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = MOVIE_CATALOG_SERVICE, fallbackMethod = "getUserRatingFallback")
    public UserRating getUserRating(String id) {
        return restTemplate.getForObject("http://rating-data-service/ratings/user/" + id, UserRating.class);
    }

    public UserRating getUserRatingFallback(String id) {
        return new UserRating(
                Arrays.asList(new Rating("FallBackMovie",0))
        );
    }
}
