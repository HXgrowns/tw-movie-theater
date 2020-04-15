package com.thoughtworks.movietheater.repository;

import com.thoughtworks.movietheater.entity.Cast;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CastRepository extends CrudRepository<Cast, Integer> {
    @Query("SELECT * from cast WHERE name = :name")
    Cast findCastByName(String name);
}
