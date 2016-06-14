package com.dsliusar.service;

import com.dsliusar.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenresByMovieId(int movieId);
}
