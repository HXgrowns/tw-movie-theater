package com.thoughtworks.movietheater.service;

import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
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
        int classfyId;
        switch (classification) {
            case "top250":
                classfyId = 1;
                break;
            case "coming_soon":
                classfyId = 2;
                break;
            case "in_theater":
                classfyId = 3;
                break;
            default:
                classfyId = 0;
                countAndMovies.put("count", Collections.singletonList("参数输入错误"));
                return countAndMovies;
        }
        int classfyCount = movieRepository.calculateCountByClassification(classfyId);
        List<Movie> movies = movieRepository.findByClassification(classfyId, start, count);

        countAndMovies.put("count", Collections.singletonList(classfyCount));
        countAndMovies.put("subject", movies);
        return countAndMovies;
    }
}
