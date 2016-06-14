package com.dsliusar.dao;

import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;

import java.util.List;
import java.util.Map;

public interface GenreDao{

    void insert(Map<String,Genre> genreMap);
    Map<Integer,String> getAllGenres();
    List<Genre> getGenresByMovieId(int movieId);

}
