package com.thoughtworks.movietheater.repository;

import com.thoughtworks.movietheater.entity.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    @Query("SELECT * FROM movie WHERE genres LIKE CONCAT('%', :genres, '%') LIMIT :start, :count")
    List<Movie> findByTag(@Param("genres") String genres,
                          @Param("start") int start,
                          @Param("count") int count);

    @Query("SELECT COUNT(id) FROM movie WHERE genres LIKE CONCAT('%', :genres, '%')")
    int totalCountByTag(@Param("genres") String genres);

    @Query("SELECT COUNT(id) from movie")
    int calculateAllCount();

    @Query("SELECT * FROM movie LIMIT :start, :count")
    List<Movie> findAllByPage(@Param("start") int start, @Param("count") int count);

    @Query("SELECT COUNT(id) from movie WHERE title LIKE CONCAT('%', :keyword, '%')")
    int calculateCountByKeyword(@Param("keyword") String keyword);

    @Query("SELECT * FROM movie WHERE title LIKE CONCAT('%', :keyword, '%') LIMIT :start, :count")
    List<Movie> findByKeyword(@Param("keyword") String keyword, @Param("start") int start, @Param("count") int count);

    @Query("SELECT COUNT(id) from movie WHERE movie_classification = :classifyId")
    int calculateCountByClassification(@Param("classifyId") int classifyId);

    @Query("SELECT * FROM movie WHERE movie_classification = :classifyId LIMIT :start, :count")
    List<Movie> findByClassification(@Param("classifyId") int classifyId, @Param("start") int start, @Param("count") int count);
}
