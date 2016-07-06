package com.dsliusar.tools.entities.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieAddRequest {
    private int movieId;
    private String movieNameRus;
    private String movieNameOrigin;
    private int year;
    private double rating;
    private double price;
    private String description;

    @JsonProperty("genres")
    private List<Integer> genres;

    @JsonProperty("countries")
    private List<Integer> countries;

    public int getMovieId() { return movieId; }

    public void setMovieId(int movieId) { this.movieId = movieId; }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public List<Integer> getCountries() {
        return countries;
    }

    public void setCountries(List<Integer> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "MovieAddRequest{" +
                "movieId=" + movieId +
                ", movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", countries=" + countries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieAddRequest that = (MovieAddRequest) o;

        if (movieId != that.movieId) return false;
        if (year != that.year) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (movieNameRus != null ? !movieNameRus.equals(that.movieNameRus) : that.movieNameRus != null) return false;
        if (movieNameOrigin != null ? !movieNameOrigin.equals(that.movieNameOrigin) : that.movieNameOrigin != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (genres != null ? !genres.equals(that.genres) : that.genres != null) return false;
        return !(countries != null ? !countries.equals(that.countries) : that.countries != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = movieId;
        result = 31 * result + (movieNameRus != null ? movieNameRus.hashCode() : 0);
        result = 31 * result + (movieNameOrigin != null ? movieNameOrigin.hashCode() : 0);
        result = 31 * result + year;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (countries != null ? countries.hashCode() : 0);
        return result;
    }
}
