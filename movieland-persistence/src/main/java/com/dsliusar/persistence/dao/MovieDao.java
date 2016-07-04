package com.dsliusar.persistence.dao;

import com.dsliusar.tools.http.entities.AllMoviesRequestDto;
import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.MovieSearchRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface MovieDao{

    void addMovie(Map<String, Movie> movieMap, Map<String, Country> countryMap, Map<String, Genre> mapGenre);
    List<Movie> getAllMovies(AllMoviesRequestDto movieSortRequest);
    List<Movie> getSearchedMovies(MovieSearchRequest movieSearchRequest);
    Movie getById(int movieId);
    Double getUserMovieRating(int userId, int movieId);
    void updateCurrentFlag(int movieId);
    void addMovie(Movie movie);
    void addMovieGenres(int movieId, int genreId);
    void addMovieCountries(int movieId, int countryId);
    void auditMovies(Movie movie);
    void deleteNotCurrentMovies();
    List<Movie> getAllInvalidMovies();
    byte[] getMoviePoster(Integer movieId);




}
