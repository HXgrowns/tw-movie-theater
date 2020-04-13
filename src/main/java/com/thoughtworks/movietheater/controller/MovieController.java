package com.thoughtworks.movietheater.controller;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/findById")
    public Movie findById(@PathVariable int id) {
        return movieService.findById(id);
    }

    @GetMapping(value = "/findAllByPage")
    public List<Movie> findAllByPage(@RequestParam(name = "currentPage") int currentPage,
                                     @RequestParam(name = "linesize") int linesize) {
        return movieService.findAllByPage(currentPage, linesize);
    }
}
