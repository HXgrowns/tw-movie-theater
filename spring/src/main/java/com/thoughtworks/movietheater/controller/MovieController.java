package com.thoughtworks.movietheater.controller;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
  @Autowired
  private MovieService movieService;

  @GetMapping("/findById/{id}")
  public Movie findById(@PathVariable int id) {
    System.out.println(id);
    return movieService.findByName(id);
  }

  @GetMapping("/findByTag/{genre}")
  public Map<String, List> findByTag(@PathVariable String genre,
                               @RequestParam("start") int start,
                               @RequestParam("count") int count) throws UnsupportedEncodingException {
    genre = URLDecoder.decode(genre, "UTF-8");

    Map<String, List> mvByTagMap = new LinkedHashMap<>();
    mvByTagMap.put("count", Arrays.asList(movieService.totalCountByTag(genre)));
    mvByTagMap.put("subjects", movieService.findByTag(genre, start, count));

    return mvByTagMap;
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
  public Map<String, List<?>> findByClassification(@RequestParam(name = "classification") String classification,
                                                   @RequestParam(name = "start") int start,
                                                   @RequestParam(name = "count") int count) {
    return movieService.findByClassification(classification, start, count);
  }
}
