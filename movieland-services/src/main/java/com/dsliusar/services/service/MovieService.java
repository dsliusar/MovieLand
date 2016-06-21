package com.dsliusar.services.service;

import com.dsliusar.http.entities.MovieSortRequest;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.http.entities.MovieSearchRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(MovieSortRequest movieSortRequest);

    List<Movie> getAllSearchedMovies(MovieSearchRequest movieSearchRequest);

    Movie getMovieById(int id);



}
