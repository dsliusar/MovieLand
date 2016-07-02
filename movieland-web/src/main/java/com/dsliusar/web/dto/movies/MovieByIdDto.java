package com.dsliusar.web.dto.movies;

import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.web.dto.converter.CountryListSerializer;
import com.dsliusar.web.dto.converter.GenreListSerializer;
import com.dsliusar.web.dto.converter.ReviewListSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "movie")
public class MovieByIdDto{

    private String movieNameRus;
    private String movieNameOrigin;
    private int year;

    @JsonProperty("totalRating")
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

    @JsonProperty("UserRating")
    private double userRating;

    @JsonProperty("priceCurrency")
    private String currency;



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

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

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
                ", userRating=" + userRating +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieByIdDto that = (MovieByIdDto) o;

        if (year != that.year) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (Double.compare(that.userRating, userRating) != 0) return false;
        if (movieNameRus != null ? !movieNameRus.equals(that.movieNameRus) : that.movieNameRus != null) return false;
        if (movieNameOrigin != null ? !movieNameOrigin.equals(that.movieNameOrigin) : that.movieNameOrigin != null)
            return false;
        if (genreList != null ? !genreList.equals(that.genreList) : that.genreList != null) return false;
        if (countryList != null ? !countryList.equals(that.countryList) : that.countryList != null) return false;
        if (reviewText != null ? !reviewText.equals(that.reviewText) : that.reviewText != null) return false;
        return !(currency != null ? !currency.equals(that.currency) : that.currency != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = movieNameRus != null ? movieNameRus.hashCode() : 0;
        result = 31 * result + (movieNameOrigin != null ? movieNameOrigin.hashCode() : 0);
        result = 31 * result + year;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (genreList != null ? genreList.hashCode() : 0);
        result = 31 * result + (countryList != null ? countryList.hashCode() : 0);
        result = 31 * result + (reviewText != null ? reviewText.hashCode() : 0);
        temp = Double.doubleToLongBits(userRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
