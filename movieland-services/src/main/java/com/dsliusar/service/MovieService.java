package com.dsliusar.service;

import com.dsliusar.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie getMovieById(int id);

}
