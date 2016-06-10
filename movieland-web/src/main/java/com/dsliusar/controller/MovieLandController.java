package com.dsliusar.controller;

import com.dsliusar.entity.Movie;
import com.dsliusar.service.MovieService;
import com.dsliusar.util.JsonManualConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieLandController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
   private MovieService movieService;

    @Autowired
    private JsonManualConverter jsonManualConverter;

    @RequestMapping(value = "/movies", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllMovies(){
        LOGGER.info("Sending request to get all movies");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = movieService.getAllMovies();
        String allMoviesJson = jsonManualConverter.allMovieToJson(movieList);
        LOGGER.info("All {} movies received, it took {} ms", allMoviesJson, System.currentTimeMillis() - startTime);
        return allMoviesJson;
    }

    @RequestMapping(value = "/movie/{movieId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId){
        LOGGER.info("Sending request to get mobie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        Movie movie = movieService.getMovieById(movieId);
        String jsonMovie = jsonManualConverter.movieInfoByIdToJson(movie);
        LOGGER.info("Id {} movie received, it took {} ms", movieId  , System.currentTimeMillis() - startTime);
        return jsonMovie;
    }
    //public void setMovieService(MovieService movieService) {
   //     this.movieService = movieService;
   // }

//    public void setJsonManualConverter(JsonManualConverter jsonManualConverter) {
//        this.jsonManualConverter = jsonManualConverter;
//    }
}
