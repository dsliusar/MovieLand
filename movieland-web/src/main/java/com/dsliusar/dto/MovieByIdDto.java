package com.dsliusar.dto;

import com.dsliusar.dto.converter.CountryListSerializer;
import com.dsliusar.dto.converter.GenreListSerializer;
import com.dsliusar.dto.converter.ReviewListSerializer;
import com.dsliusar.entity.Country;
import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "movie")
public class MovieByIdDto{

    private String movieNameRus;
    private String movieNameOrigin;
    private int year;
    private double rating;

    @JsonSerialize(using = GenreListSerializer.class)
    @JsonProperty("genres")
    private List<Genre> genreList;

    @JsonSerialize(using = CountryListSerializer.class)
    @JsonProperty("countries")
    private List<Country> countryList;

    @JsonSerialize(using = ReviewListSerializer.class)
    @JsonProperty("reviews")
    private List<Review> reviewText;

    @Override
    public String toString() {
        return "MovieByIdDto{" +
                "movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", genreList=" + genreList +
                ", countryList=" + countryList +
                ", reviewText=" + reviewText +
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

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Review> getReviewText() {
        return reviewText;
    }

    public void setReviewText(List<Review> reviewText) {
        this.reviewText = reviewText;
    }

}
