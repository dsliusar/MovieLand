package com.dsliusar.persistence.dao;

import com.dsliusar.tools.http.entities.MovieSortRequest;
import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.MovieSearchRequest;

import java.util.List;
import java.util.Map;

public interface MovieDao{

    void addMovie(Map<String, Movie> movieMap, Map<String, Country> countryMap, Map<String, Genre> mapGenre);
    List<Movie> getAllMovies(MovieSortRequest movieSortRequest);
    List<Movie> getSearchedMovies(MovieSearchRequest movieSearchRequest);
    Movie getById(int movieId);
    void updateCurrentFlag(int movieId);
    void addMovie(Movie movie);
    void addMovieGenres(int movieId, int genreId);
    void addMovieCountries(int movieId, int countryId);




}
