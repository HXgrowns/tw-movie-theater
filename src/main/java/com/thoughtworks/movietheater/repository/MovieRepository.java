package com.thoughtworks.movietheater.repository;

import com.thoughtworks.movietheater.entity.Movie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    @Query("SELECT * from movie limit :currentPage,:linesize")
    List<Movie> findAllByPage(int currentPage, int linesize);

}
