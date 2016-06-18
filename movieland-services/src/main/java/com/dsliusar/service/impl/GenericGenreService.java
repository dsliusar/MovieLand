package com.dsliusar.service.impl;

import com.dsliusar.dao.GenreDao;
import com.dsliusar.entity.Genre;
import com.dsliusar.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericGenreService implements GenreService {

    @Autowired
    private GenreDao jdbcGenreDao;

    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        return jdbcGenreDao.getGenresByMovieId(movieId);
    }

    @Override
    public Map<Integer,List<Genre>> getAllMoviesGenres() {
        return null;
    }

    @Override
    public Map<String, Integer> getAllGenres() {
        return jdbcGenreDao.getAllGenres();
    }


}
