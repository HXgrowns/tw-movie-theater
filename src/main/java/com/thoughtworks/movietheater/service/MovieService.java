package com.thoughtworks.movietheater.service;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Map<String, List<?>> findAllByPage(int currentPage, int linesize) {
        Map<String, List<?>> countAndMovies = new HashMap<>();
        int count = movieRepository.calculateAllCount();
        List<Movie> movies = movieRepository.findAllByPage(currentPage, linesize);
        countAndMovies.put("count", Collections.singletonList(count));
        countAndMovies.put("subject", movies);
        return countAndMovies;
    }

    public Map<String, List<?>> findByKeyword(String keyword, int currentPage, int linesize) {
        Map<String, List<?>> countAndMovies = new HashMap<>();
        int count = movieRepository.calculateCountByKeyword("%" + keyword + "%");
        List<Movie> movies = movieRepository.findByKeyword("%" + keyword + "%", currentPage, linesize);
        countAndMovies.put("count", Collections.singletonList(count));
        countAndMovies.put("subject", movies);
        return countAndMovies;
    }
}
