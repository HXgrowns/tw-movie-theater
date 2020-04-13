package com.thoughtworks.movietheater.repository;

import com.thoughtworks.movietheater.entity.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    @Query("SELECT COUNT(id) from movie")
    int calculateAllCount();

    @Query("SELECT * FROM movie LIMIT :currentPage, :linesize")
    List<Movie> findAllByPage(int currentPage, int linesize);

    @Query("SELECT COUNT(id) from movie WHERE title LIKE :keyword")
    int calculateCountByKeyword(String keyword);

    @Query("SELECT * FROM movie WHERE title LIKE :keyword LIMIT :currentPage, :linesize")
    List<Movie> findByKeyword(String keyword, int currentPage, int linesize);

}
