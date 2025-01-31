package com.incubation.movie_catalouge_service.controller;

import com.incubation.movie_catalouge_service.model.CatlogItems;
import com.incubation.movie_catalouge_service.model.Movie;
import com.incubation.movie_catalouge_service.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController
{

    @GetMapping("/{userId}")
    public List<CatlogItems> getCatalogItems(@PathVariable("userId") String id){

        RestTemplate restTemplate = new RestTemplate();

        List<Rating> ratings = Arrays.asList(new Rating("1234",4),new Rating("532",5));

//        Movie forObject = restTemplate.getForObject("http://localhost:8082/movies/{movieId}", Movie.class);

        List<CatlogItems> catlogItems = ratings.stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatlogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
                }
        ).collect(Collectors.toList());

        return catlogItems;
    }
}
