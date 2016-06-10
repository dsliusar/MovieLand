package com.dsliusar.dao;

import com.dsliusar.entity.Movie;

import java.util.List;

public interface MovieDao  extends CommonDao {

    void insert();
    List<Movie> getAllMovies();
    Movie getById(int id);
}
