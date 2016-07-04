package com.dsliusar.web.dto.movies;

import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.web.dto.converter.GenreListSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "movie")
@JsonPropertyOrder({"movieNameRus","movieNameOrigin","year","rating", "genre","price","currency"})
@XmlType (propOrder = {"movieNameRus","movieNameOrigin","year","rating", "genre","price","currency"})
public class AllMovieDto{

    private String movieNameRus;

    private String movieNameOrigin;

    private int year;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double rating;

    @XmlElementWrapper(name = "genres")
    @JsonSerialize(using = GenreListSerializer.class)
    private List<Genre> genre;

    @JsonProperty("priceCurrency")
    private String currency;

    private Double price;

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AllMovieDto{" +
                "movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", genre=" + genre +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllMovieDto that = (AllMovieDto) o;

        if (year != that.year) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (movieNameRus != null ? !movieNameRus.equals(that.movieNameRus) : that.movieNameRus != null) return false;
        if (movieNameOrigin != null ? !movieNameOrigin.equals(that.movieNameOrigin) : that.movieNameOrigin != null)
            return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
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
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
