package com.dsliusar.persistence.dao;

import com.dsliusar.persistence.entity.Genre;

import java.util.List;
import java.util.Map;

public interface GenreDao{

    void insert(Map<String,Genre> genreMap);
    Map<String,Integer> getAllGenres();
    List<Genre> getGenresByMovieId(int movieId);
    Map<Integer,List<Genre>> getGenreWithMovieId();

}
