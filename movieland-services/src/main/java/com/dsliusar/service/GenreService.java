package com.dsliusar.service;

import com.dsliusar.entity.Genre;

import java.util.List;
import java.util.Map;

public interface GenreService {

    List<Genre> getGenresByMovieId(int movieId);
    Map<Integer,List<Genre>> getAllMoviesGenres();
    Map<String,Integer> getAllGenres();
}
