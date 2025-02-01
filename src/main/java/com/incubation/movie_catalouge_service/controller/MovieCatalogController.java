package com.incubation.movie_catalouge_service.controller;

import com.incubation.movie_catalouge_service.model.CatlogItems;
import com.incubation.movie_catalouge_service.model.Movie;
import com.incubation.movie_catalouge_service.model.Rating;
import com.incubation.movie_catalouge_service.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    @Autowired
    RestTemplate restTemplate; //Is going to be depriciated near soon

//    @Autowired
//    WebClient.Builder webClientBuilder; //This is new way of calling microservices

    @GetMapping("/{userId}")
    public List<CatlogItems> getCatalogItems(@PathVariable("userId") String id) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratings/user/" + id, UserRating.class);

        // Using Rest Teamplate to call other service
        List<CatlogItems> catlogItems = ratings.getRatings().stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatlogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
                }
        ).collect(Collectors.toList());

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
}
