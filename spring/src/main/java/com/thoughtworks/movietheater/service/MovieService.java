package com.thoughtworks.movietheater.service;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
