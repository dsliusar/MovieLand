package com.dsliusar.service;

import com.dsliusar.entity.Movie;
import com.dsliusar.dto.MovieSearchRequestDto;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(String ratingOrder,String priceOrder);

    List<Movie> getAllSearchedMovies(MovieSearchRequestDto movieSearchRequest);

    Movie getMovieById(int id);



}
