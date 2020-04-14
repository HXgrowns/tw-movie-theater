package com.example.movietheater.repository;

import com.example.movietheater.entity.Cast;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CastRepository extends CrudRepository<Cast, Integer> {
    @Query("SELECT * from cast WHERE name = :name")
    Cast findCastByName(@Param("name") String name);
}
