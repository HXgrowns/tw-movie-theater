package com.thoughtworks.movietheater.service;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {
  @Autowired
  private MovieRepository movieRepository;

  public Movie findByName(int id) {
    Optional<Movie> byId = movieRepository.findById(id);
    return byId.orElse(null);
  }

  public List<Movie> findByTag(String tag, int start, int count) {
    List<Movie> mvListFindByTag = movieRepository.findByTag(tag, start, count);
    return mvListFindByTag;
  }

  public int totalCountByTag(String tag) {
    return movieRepository.totalCountByTag(tag);
  }

  public Map<String, List<?>> findAllByPage(int start, int count) {
    Map<String, List<?>> countAndMovies = new HashMap<>();
    int allCount = movieRepository.calculateAllCount();
    List<Movie> movies = movieRepository.findAllByPage(start, count);

    countAndMovies.put("count", Collections.singletonList(allCount));
    countAndMovies.put("subject", movies);
    return countAndMovies;
  }

  public Map<String, List<?>> findByKeyword(String keyword, int start, int count) {
    Map<String, List<?>> countAndMovies = new HashMap<>();
    int keywordCount = movieRepository.calculateCountByKeyword("%" + keyword + "%");
    List<Movie> movies = movieRepository.findByKeyword("%" + keyword + "%", start, count);

    countAndMovies.put("count", Collections.singletonList(keywordCount));
    countAndMovies.put("subject", movies);
    return countAndMovies;
  }

  public Map<String, List<?>> findByClassification(String classification, int start, int count) {
    Map<String, List<?>> countAndMovies = new HashMap<>();
    int classifyId;
    switch (classification) {
      case "top250":
        classifyId = 1;
        break;
      case "coming_soon":
        classifyId = 2;
        break;
      case "in_theater":
        classifyId = 3;
        break;
      default:
        classifyId = 0;
        countAndMovies.put("count", Collections.singletonList("参数输入错误"));
        return countAndMovies;
    }
    int classifyCount = movieRepository.calculateCountByClassification(classifyId);
    List<Movie> movies = movieRepository.findByClassification(classifyId, start, count);

    countAndMovies.put("count", Collections.singletonList(classifyCount));
    countAndMovies.put("subject", movies);
    return countAndMovies;
  }
}
