package com.dsliusar.services.service;

import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.MovieAddRequest;
import com.dsliusar.tools.http.entities.MovieSearchRequest;
import com.dsliusar.tools.http.entities.AllMoviesRequestDto;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies(AllMoviesRequestDto movieSortRequest);

    List<Movie> getAllSearchedMovies(MovieSearchRequest movieSearchRequest);

    Movie getMovieById(int movieId);

    Double getUserRating(int userId, int movieId);

    void updateCurrentFlag(int movieId);

    void addMovie(MovieAddRequest movieAddRequest);

    void updateMovie(MovieAddRequest movieAddRequest);

    double updateAverageRating(int movieId, List<Double> usersRating);

    void performAuditOfMovies();
}
