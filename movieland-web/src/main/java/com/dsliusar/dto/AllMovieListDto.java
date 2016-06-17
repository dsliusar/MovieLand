package com.dsliusar.dto;

import org.springframework.http.ResponseEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "movies")
public class AllMovieListDto{

    private List<AllMovieDto> movie = new ArrayList<>();

    public AllMovieListDto() {
    }

    public AllMovieListDto(List<AllMovieDto> movies) {
        this.movie = movies;
    }

    public AllMovieListDto(ResponseEntity<List<AllMovieDto>> listResponseEntity) {
        this.movie = movie;
    }


    public List<AllMovieDto> getMovie() {
        return movie;
    }

    public void setMovie(List<AllMovieDto> movie) {
        this.movie = movie;
    }
}
