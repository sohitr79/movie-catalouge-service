package com.incubation.movie_catalouge_service.controller;

import com.incubation.movie_catalouge_service.client.MovieClient;
import com.incubation.movie_catalouge_service.client.RatingsClient;
import com.incubation.movie_catalouge_service.model.CatlogItems;
import com.incubation.movie_catalouge_service.model.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    @Autowired
    private RestTemplate restTemplate; //Is going to be depriciated near soon

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private RatingsClient ratingsClient;

//    @Autowired
//    WebClient.Builder webClientBuilder; //This is new way of calling microservices

    @GetMapping("/{userId}")
    public List<CatlogItems> getCatalogItems(@PathVariable("userId") String id) {

        //Calling User rating to fetch what all movies user has rated
        //rating-data-service this mapping is happening inside eureka server
        UserRating ratings = ratingsClient.getUserRating(id);

        // Using Rest Teamplate to call other service
        List<CatlogItems> catlogItems = movieClient.getCatalogItems(ratings);

        return catlogItems;

        //Using WebClient to call other service

//        List<CatlogItems> catlogItems1 = ratings.stream().map(rating -> {
//                    Movie movie = webClientBuilder.build()
//                            .get() //tells it's a get call
//                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                            .retrieve() //collect what ever is returned by URI
//                            .bodyToMono(Movie.class) //Mono is promise that its going to return something
//                            .block(); //Since this call is Async we are blocking further execution until mono is return
//                    return new CatlogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
//                }
//
//        ).collect(Collectors.toList());
//
//        return catlogItems1;
    }


    public List<CatlogItems> getCatalogFallback(@PathVariable("userId") String id,  Throwable throwable) {
        return Arrays.asList(new CatlogItems("No movie","FallBack due to : "+throwable.getMessage(),0));
    }
}
