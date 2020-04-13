package com.thoughtworks.movietheater.repository;

import com.thoughtworks.movietheater.entity.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
  @Query("SELECT * FROM movies WHERE genres LIKE CONCAT('%', :genres, '%') LIMIT :start, :count")
  List<Movie> findByTag (@Param("genres") String genres,
                         @Param("start") int start,
                         @Param("count") int count);

  @Query("SELECT COUNT(id) FROM movies WHERE genres LIKE CONCAT('%', :genres, '%')")
  int totalCountByTag (@Param("genres") String genres);
}
