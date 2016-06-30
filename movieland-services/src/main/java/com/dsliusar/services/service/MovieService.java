package com.dsliusar.services.service;

import com.dsliusar.tools.http.entities.MovieSortRequest;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.MovieSearchRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(MovieSortRequest movieSortRequest);

    List<Movie> getAllSearchedMovies(MovieSearchRequest movieSearchRequest);

    Movie getMovieById(int movieId);

    void updateCurrentFlag(int movieId);

    void addMovie(Movie movie);

    double updateAverageRating(int movieId, List<Double> usersRating);
}
