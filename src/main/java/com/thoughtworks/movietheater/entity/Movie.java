package com.thoughtworks.movietheater.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Table("movie")
public class Movie {
    @Id
    private int id;
    @Column("alt")
    private String alt;
    @Column("year")
    private Date year;
    @Column("title")
    private String title;
    @Column("rating")
    private float rating;
    @Column("original_title")
    private String originalTitle;
    @Column("directors")
    private String[] directors;
    @Column("casts")
    private String[] casts;
    @Column("genres")
    private String[] genres;
    @Column("image")
    private String image;

    public Movie(int id, String alt, Date year, String title, float rating, String originalTitle, String directors, String casts, String genres, String image) {
        this.id = id;
        this.alt = alt;
        this.year = year;
        this.title = title;
        this.rating = rating;
        this.originalTitle = originalTitle;
        this.directors = directors.split(",");
        this.casts = casts.split(",");
        this.genres = genres.split(",");
        this.image = image;
    }

}
