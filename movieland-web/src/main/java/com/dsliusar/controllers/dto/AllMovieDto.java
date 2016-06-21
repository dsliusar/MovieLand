package com.dsliusar.controllers.dto;

import com.dsliusar.controllers.dto.converter.GenreListSerializer;
import com.dsliusar.persistence.entity.Genre;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "movie")
@XmlType (propOrder = {"movieNameRus","movieNameOrigin","year","rating", "genres"} )
public class AllMovieDto{


    private String movieNameRus;

    private String movieNameOrigin;

    private int year;

    private double rating;

    @JsonSerialize(using = GenreListSerializer.class)
    private List<Genre> genres;

    @Override
    public String toString() {
        return "AllMovieDto{" +
                "movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", genres=" + genres +
                '}';
    }

    public String getMovieNameRus() {
        return movieNameRus;
    }

    public void setMovieNameRus(String movieNameRus) {
        this.movieNameRus = movieNameRus;
    }

    public String getMovieNameOrigin() {
        return movieNameOrigin;
    }

    public void setMovieNameOrigin(String movieNameOrigin) {
        this.movieNameOrigin = movieNameOrigin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
