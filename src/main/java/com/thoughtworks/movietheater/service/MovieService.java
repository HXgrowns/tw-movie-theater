package com.thoughtworks.movietheater.service;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> findAllByPage(int currentPage, int linesize) {
        return movieRepository.findAllByPage(currentPage, linesize);
    }
}
