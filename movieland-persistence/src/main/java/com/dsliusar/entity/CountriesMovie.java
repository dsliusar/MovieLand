package com.dsliusar.entity;

public class CountriesMovie {

    private int countryMovieId;
    private int movieId;
    private int countryId;

    @Override
    public String toString() {
        return "CountriesMovie{" +
                "countryMovieId=" + countryMovieId +
                ", movieId=" + movieId +
                ", countryId=" + countryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountriesMovie that = (CountriesMovie) o;

        if (countryMovieId != that.countryMovieId) return false;
        if (movieId != that.movieId) return false;
        return countryId == that.countryId;

    }

    @Override
    public int hashCode() {
        int result = countryMovieId;
        result = 31 * result + movieId;
        result = 31 * result + countryId;
        return result;
    }

    public int getCountryMovieId() {
        return countryMovieId;
    }

    public void setCountryMovieId(int countryMovieId) {
        this.countryMovieId = countryMovieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
