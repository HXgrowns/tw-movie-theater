package com.thoughtworks.movietheater.controller;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/findById")
    public Movie findById(@RequestParam int id) {
        return movieService.findById(id);
    }

    @GetMapping(value = "/findAllByPage")
    public Map<String, List<?>> findAllByPage(@RequestParam(name = "start") int start,
                                              @RequestParam(name = "count") int count) {
        return movieService.findAllByPage(start, count);
    }

    @GetMapping(value = "/findByKeyword")
    public Map<String, List<?>> findByKeyword(@RequestParam(name = "keyword") String keyword,
                                              @RequestParam(name = "start") int start,
                                              @RequestParam(name = "count") int count) {
        return movieService.findByKeyword(keyword, start, count);
    }

    @GetMapping(value = "/findByClassification")
    public Map<String, List<?>> findByClaasification(@RequestParam(name = "classification") String classification,
                                                     @RequestParam(name = "start") int start,
                                                     @RequestParam(name = "count") int count) {
        return movieService.findByClassification(classification, start, count);
    }
}
