package com.thoughtworks.movietheater.controller;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Map<String, List<?>> findAllByPage(@RequestParam(name = "currentPage") int currentPage,
                                              @RequestParam(name = "linesize") int linesize) {
        return movieService.findAllByPage(currentPage, linesize);
    }

    @GetMapping(value = "/findByKeyword")
    public Map<String, List<?>> findByKeyword(@RequestParam(name = "keyword") String keyword,
                                              @RequestParam(name = "currentPage") int currentPage,
                                              @RequestParam(name = "linesize") int linesize) {
        return movieService.findByKeyword(keyword, currentPage, linesize);
    }
}
