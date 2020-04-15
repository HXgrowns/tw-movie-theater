package com.thoughtworks.movietheater.service;

import com.thoughtworks.movietheater.entity.Cast;
import com.thoughtworks.movietheater.entity.Movie;
import com.thoughtworks.movietheater.repository.CastRepository;
import com.thoughtworks.movietheater.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CastRepository castRepository;

    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Map<String, List<?>> findAllByPage(int start, int count) {
        Map<String, List<?>> countAndMovies = new HashMap<>();
        int allCount = movieRepository.calculateAllCount();
        List<Movie> movies = movieRepository.findAllByPage(start, count);

        countAndMovies.put("count", Collections.singletonList(allCount));
        countAndMovies.put("subjects", movies);
        return countAndMovies;
    }

    public Map<String, List<?>> findByKeyword(String keyword, int start, int count) {
        Map<String, List<?>> countAndMovies = new HashMap<>();
        int keywordCount = movieRepository.calculateCountByKeyword(keyword);
        List<Movie> movies = movieRepository.findByKeyword(keyword, start, count);

        countAndMovies.put("count", Collections.singletonList(keywordCount));
        countAndMovies.put("subjects", movies);
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
        countAndMovies.put("subjects", movies);
        countAndMovies.put("title", Arrays.asList(classification));
        return countAndMovies;
    }

    public List<Cast> findCastsByMovieId(int movieId) {
        List<Cast> casts = new ArrayList<>();

        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return casts;
        }

        for (String name : movie.getCasts()) {
            Cast cast = castRepository.findCastByName(name);
            if (cast != null) {
                casts.add(cast);
            }
        }

        return casts;
    }

    public Map<String, List> findByTag(String tag, int start, int count) {
        Map<String, List> mvByTagMap = new LinkedHashMap<>();
        mvByTagMap.put("count", Arrays.asList(movieRepository.totalCountByTag(tag)));
        mvByTagMap.put("subjects", movieRepository.findByTag(tag, start, count));
        return mvByTagMap;
    }
}
