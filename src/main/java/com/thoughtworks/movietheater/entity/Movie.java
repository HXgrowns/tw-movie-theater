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
    @Column("durations")
    private String durations;
    @Column("image")
    private String image;
    @Column("summary")
    private String summary;
    @Column("movie_classification")
    private int classification;

    public int getId() {
        return id;
    }

    public String getAlt() {
        return alt;
    }

    public Date getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String[] getDirectors() {
        return directors;
    }

    public String[] getCasts() {
        return casts;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getDurations() {
        return durations;
    }

    public String getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public int getClassification() {
        return classification;
    }
}
