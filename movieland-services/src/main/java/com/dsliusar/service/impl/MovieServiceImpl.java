package com.dsliusar.service.impl;

import com.dsliusar.dao.GenreDao;
import com.dsliusar.dao.MovieDao;
import com.dsliusar.dao.ReviewDao;
import com.dsliusar.entity.Movie;
import com.dsliusar.entity.Review;
import com.dsliusar.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private GenreDao genreDao;

    @Override
    public List<Movie> getAllMovies()
    {   List<Movie> movieList;
        movieList = movieDao.getAllMovies();
//        Map<Integer,String> genresHashMap = genreDao.getAllGenres();
//        for (Movie movie : movieList){
//            for(Genre genre : movie.getGenreList()){
//                if (genresHashMap.containsKey(genre.getGenreId())){
//
//                }
//            }
//
//        }
//        if(genresHashMap.)
        return movieList;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDao.getById(id);
        List<String> reviewTextList = new ArrayList<>();
        for (Review review : reviewDao.getReviewsByMovieId(movie.getMovieId()) ){
            reviewTextList.add(review.getReviewText());
        }
        movie.setReviewText(reviewTextList);
        return movie;


    }
}
