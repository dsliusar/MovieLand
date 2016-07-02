package com.dsliusar.web.dto.movies;

import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.web.dto.converter.CountryListSerializer;
import com.dsliusar.web.dto.converter.GenreListSerializer;
import com.dsliusar.web.dto.converter.ReviewListSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "movie")
public class MovieByIdDto{

    private String movieNameRus;
    private String movieNameOrigin;
    private Integer year;

    @JsonProperty("totalRating")
    private Double rating;

    @JsonSerialize(using = GenreListSerializer.class)
    @JsonProperty("genres")
    private List<Genre> genreList;

    @JsonSerialize(using = CountryListSerializer.class)
    @JsonProperty("countries")
    private List<Country> countryList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ReviewListSerializer.class)
    @JsonProperty("reviews")
    private List<Review> reviewText;

    @JsonProperty("UserRating")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double userRating;

    @JsonProperty("priceCurrency")
    private String currency;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double price;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
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

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
                ", price=" + price +
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
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        return !(price != null ? !price.equals(that.price) : that.price != null);

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
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
