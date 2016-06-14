package com.dsliusar.service.impl;

import com.dsliusar.dao.GenreDao;
import com.dsliusar.entity.Genre;
import com.dsliusar.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleGenreService implements GenreService {

    @Autowired
    private GenreDao jdbcGenreDao;

    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        return jdbcGenreDao.getGenresByMovieId(movieId);
    }
}
