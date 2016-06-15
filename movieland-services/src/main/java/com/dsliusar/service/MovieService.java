package com.dsliusar.service;

import com.dsliusar.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(String ratingOrder,String priceOrder);

    Movie getMovieById(int id);

}
