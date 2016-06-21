package com.dsliusar.services.service;

import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.http.entities.MovieSearchRequestDto;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(String ratingOrder,String priceOrder);

    List<Movie> getAllSearchedMovies(MovieSearchRequestDto movieSearchRequest);

    Movie getMovieById(int id);



}
