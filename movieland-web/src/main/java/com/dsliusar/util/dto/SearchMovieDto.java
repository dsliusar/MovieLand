package com.dsliusar.util.dto;

import java.io.Serializable;

/**
 * Created by DSliusar on 15.06.2016.
 */
public class SearchMovieDto implements Serializable{

    private String movieNameRus;

    private String movieNameOrigin;

    private int year;

    private String country;

    private String genreName;

    @Override
    public String toString() {
        return "SearchMovieDto{" +
                "movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", genreName='" + genreName + '\'' +
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }


}
