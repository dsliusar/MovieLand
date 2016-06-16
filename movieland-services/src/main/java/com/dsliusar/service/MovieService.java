package com.dsliusar.service;

import com.dsliusar.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(String ratingOrder,String priceOrder);

    List<Movie> getAllSearchedMovies(String movieNameRus,String movieNameOrigin
                                    ,String country,Integer year,String genreName);

    Movie getMovieById(int id);



}
