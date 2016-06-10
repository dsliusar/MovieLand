package com.dsliusar.entity;

import java.util.List;

public class Movie {
    private int movieId;
    private String movieNameRus;
    private String movieNameeEng;
    private int year;
    private String country;
    private int genre_movie_id;
    private String desciprtion;
    private double rating;
    private double price;

    private List<Genre> genreList;
    private List<Country> countryList;
    private List<String> reviewText;

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieNameRus='" + movieNameRus + '\'' +
                ", movieNameeEng='" + movieNameeEng + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", genre_movie_id=" + genre_movie_id +
                ", desciprtion='" + desciprtion + '\'' +
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
        if (genre_movie_id != movieDao.genre_movie_id) return false;
        if (Double.compare(movieDao.rating, rating) != 0) return false;
        if (Double.compare(movieDao.price, price) != 0) return false;
        if (movieNameRus != null ? !movieNameRus.equals(movieDao.movieNameRus) : movieDao.movieNameRus != null)
            return false;
        if (movieNameeEng != null ? !movieNameeEng.equals(movieDao.movieNameeEng) : movieDao.movieNameeEng != null)
            return false;
        if (country != null ? !country.equals(movieDao.country) : movieDao.country != null) return false;
        return !(desciprtion != null ? !desciprtion.equals(movieDao.desciprtion) : movieDao.desciprtion != null);

    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = movieId;
        result = 31 * result + (movieNameRus != null ? movieNameRus.hashCode() : 0);
        result = 31 * result + (movieNameeEng != null ? movieNameeEng.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + genre_movie_id;
        result = 31 * result + (desciprtion != null ? desciprtion.hashCode() : 0);
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

    public String getMovieNameeEng() {
        return movieNameeEng;
    }

    public void setMovieNameeEng(String movieNameeEng) {
        this.movieNameeEng = movieNameeEng;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGenre_movie_id() {
        return genre_movie_id;
    }

    public void setGenre_movie_id(int genre_movie_id) {
        this.genre_movie_id = genre_movie_id;
    }

    public String getDesciprtion() {
        return desciprtion;
    }

    public void setDesciprtion(String desciprtion) {
        this.desciprtion = desciprtion;
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

    public List<String> getReviewText() {
        return reviewText;
    }

    public void setReviewText(List<String> reviewText) {
        this.reviewText = reviewText;
    }
}
