package com.incubation.movie_catalouge_service.controller;

import com.incubation.movie_catalouge_service.model.CatlogItems;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController
{

    @GetMapping("/{userId}")
    public List<CatlogItems> getCatalogItems(@PathVariable("userId") String id){
        return Collections.singletonList(new CatlogItems("DDLJ","LoveStory",5));
    }
}
