package com.dsliusar.dao;

import com.dsliusar.entity.Genre;

import java.util.Map;

public interface GenreDao{

    void insert(Map<String,Genre> genreMap);
    Map<Integer,String> getAllGenres();

}
