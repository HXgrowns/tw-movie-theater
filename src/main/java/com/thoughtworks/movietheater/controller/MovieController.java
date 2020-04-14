package com.thoughtworks.movietheater.controller;

import com.thoughtworks.movietheater.entity.Cast;
import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @CrossOrigin("*")
    @GetMapping(value = "/findById/{id}")
    public Movie findById(@PathVariable int id) {
        return movieService.findById(id);
    }

    @CrossOrigin("*")
    @GetMapping(value = "/findAllByPage")
    public Map<String, List<?>> findAllByPage(@RequestParam(name = "start") int start,
                                              @RequestParam(name = "count") int count) {
        return movieService.findAllByPage(start, count);
    }

    @CrossOrigin("*")
    @GetMapping(value = "/findByKeyword")
    public Map<String, List<?>> findByKeyword(@RequestParam(name = "keyword") String keyword,
                                              @RequestParam(name = "start") int start,
                                              @RequestParam(name = "count") int count) {
        return movieService.findByKeyword(keyword, start, count);
    }

    @CrossOrigin("*")
    @GetMapping(value = "/findByClassification/{classification}")
    public Map<String, List<?>> findByClassification(@PathVariable String classification,
                                                     @RequestParam(name = "start") int start,
                                                     @RequestParam(name = "count") int count) {
        return movieService.findByClassification(classification, start, count);
    }

    @CrossOrigin("*")
    @GetMapping(value = "/findCastsByMovieId")
    public List<Cast> findCastsByMovieId(@RequestParam(name = "movieId") int movieId) {
        return movieService.findCastsByMovieId(movieId);
    }

    @CrossOrigin("*")
    @GetMapping("/findByTag/{genre}")
    public Map<String, List> findByTag(@PathVariable String genre,
                                       @RequestParam("start") int start,
                                       @RequestParam("count") int count) throws UnsupportedEncodingException {
        genre = URLDecoder.decode(genre, "UTF-8");

        Map<String, List> mvByTagMap = movieService.findByTag(genre, start, count);

        return mvByTagMap;
    }
}
