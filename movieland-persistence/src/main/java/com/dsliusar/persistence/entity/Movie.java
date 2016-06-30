package com.dsliusar.persistence.entity;

import com.dsliusar.tools.http.entities.MovieAddRequest;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable{

    private int movieId;
    private String movieNameRus;
    private String movieNameOrigin;
    private int year;
    private String description;
    private double rating;
    private double price;
    private List<Genre> genreList;
    private List<Country> countryList;
    private List<Review> reviewText;

    public Movie(){}

    public Movie(MovieAddRequest movieAddRequest) {
        this.movieId = movieAddRequest.getMovieId();
        this.movieNameRus = movieAddRequest.getMovieNameRus();
        this.movieNameOrigin = movieAddRequest.getMovieNameOrigin();
        this.year = movieAddRequest.getYear();
        this.description = movieAddRequest.getDescription();
        this.rating = movieAddRequest.getRating();
        this.price = movieAddRequest.getPrice();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movieDao = (Movie) o;

        if (movieId != movieDao.movieId) return false;
        if (Double.compare(movieDao.rating, rating) != 0) return false;
        if (Double.compare(movieDao.price, price) != 0) return false;
        if (movieNameRus != null ? !movieNameRus.equals(movieDao.movieNameRus) : movieDao.movieNameRus != null)
            return false;
        if (movieNameOrigin != null ? !movieNameOrigin.equals(movieDao.movieNameOrigin) : movieDao.movieNameOrigin != null)
            return false;
        return !(description != null ? !description.equals(movieDao.description) : movieDao.description != null);

    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = movieId;
        result = 31 * result + (movieNameRus != null ? movieNameRus.hashCode() : 0);
        result = 31 * result + (movieNameOrigin != null ? movieNameOrigin.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Genre> getGenreList() {
        return genreList;
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
