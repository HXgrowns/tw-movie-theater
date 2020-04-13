package com.thoughtworks.movietheater.repository;

import com.thoughtworks.movietheater.entity.Cast;
import com.thoughtworks.movietheater.entity.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    @Query("SELECT COUNT(id) from movie")
    int calculateAllCount();

    @Query("SELECT * FROM movie LIMIT :start, :count")
    List<Movie> findAllByPage(int start, int count);

    @Query("SELECT COUNT(id) from movie WHERE title LIKE CONCAT('%', :keyword, '%')")
    int calculateCountByKeyword(String keyword);

    @Query("SELECT * FROM movie WHERE title LIKE CONCAT('%', :keyword, '%') LIMIT :start, :count")
    List<Movie> findByKeyword(String keyword, int start, int count);

    @Query("SELECT COUNT(id) from movie WHERE classification = :classfyId")
    int calculateCountByClassification(int classfyId);

    @Query("SELECT * FROM movie WHERE classification = :classfyId LIMIT :start, :count")
    List<Movie> findByClassification(int classfyId, int start, int count);
}
