package com.thoughtworks.movietheater.controller;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/findById")
    public Movie findById(int id) {
        return movieService.findById(id);
    }
}
