package com.dsliusar.util.dto;

import com.dsliusar.entity.Genre;
import com.dsliusar.util.dto.converter.GenreListSerialize;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "Movie")
public class AllMovieDto implements Serializable{


    private String movieNameRus;

    private String movieNameOrigin;

    private int year;

    private double rating;

    //@JsonSerialize(using = GenreListSerialize.class)
    private List<Genre> genreList;

    @Override
    public String toString() {
        return "AllMovieDto{" +
                "movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", genreList=" + genreList +
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

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}
