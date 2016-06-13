package com.dsliusar.dao;

import com.dsliusar.entity.Country;
import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieDao{

    void insert(Map<String, Movie> movieMap, Map<String,Country> countryMap, Map<String, Genre> mapGenre);
    List<Movie> getAllMovies();
    Movie getById(int id);
}
