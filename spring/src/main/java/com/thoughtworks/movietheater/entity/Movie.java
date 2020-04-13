package com.thoughtworks.movietheater.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.util.Arrays;

@Table("movies")
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
    @Column("durations")
    private String durations;
    @Column("summary")
    private String summary;
    @Column("movie_classification")
    private int movieClassification;

    public Movie(int id, String alt, Date year, String title, float rating, String originalTitle, String[] directors, String[] casts, String[] genres, String image, String durations, String summary, int movieClassification) {
        this.id = id;
        this.alt = alt;
        this.year = year;
        this.title = title;
        this.rating = rating;
        this.originalTitle = originalTitle;
        this.directors = directors;
        this.casts = casts;
        this.genres = genres;
        this.image = image;
        this.durations = durations;
        this.summary = summary;
        this.movieClassification = movieClassification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String[] getCasts() {
        return casts;
    }

    public void setCasts(String[] casts) {
        this.casts = casts;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDurations() {
        return durations;
    }

    public void setDurations(String durations) {
        this.durations = durations;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getMovieClassification() {
        return movieClassification;
    }

    public void setMovieClassification(int movieClassification) {
        this.movieClassification = movieClassification;
    }
}
