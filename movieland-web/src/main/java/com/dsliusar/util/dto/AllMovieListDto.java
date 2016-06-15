package com.dsliusar.util.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSliusar on 15.06.2016.
 */
@XmlRootElement(name = "Movies")
public class AllMovieListDto implements Serializable {


    private List<AllMovieDto> movies = new ArrayList<>();

    public AllMovieListDto() {
    }

    public AllMovieListDto(List<AllMovieDto> movies) {
        this.movies = movies;
    }

    public List<AllMovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<AllMovieDto> movies) {
        this.movies = movies;
    }
}
