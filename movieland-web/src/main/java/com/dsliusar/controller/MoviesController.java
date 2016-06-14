package com.dsliusar.controller;

import com.dsliusar.entity.Movie;
import com.dsliusar.service.MovieService;
import com.dsliusar.util.JsonManualConverter;
import com.dsliusar.util.converter.JsonToXmlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/v1")
public class MoviesController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService simpleMovieService;

    @Autowired
    private JsonManualConverter jsonManualConverter;

    @RequestMapping(value = "/movies"
                  , method = RequestMethod.GET
                  , produces = "application/json;application/xml;charset=UTF-8")
    @ResponseBody
    public String getAllMoviesJSON(){
        LOGGER.info("Sending request to get all movies");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = simpleMovieService.getAllMovies();
        String allMoviesJson = jsonManualConverter.allMovieToJson(movieList);
        LOGGER.info("All {} movies received, it took {} ms", allMoviesJson, System.currentTimeMillis() - startTime);
        return allMoviesJson;
    }

    @RequestMapping(value = "/movies.xml"
                  , method = RequestMethod.GET
                  , produces = "application/xml;charset=UTF-8")
    @ResponseBody
    public String getAllMoviesXML(){
        LOGGER.info("Sending request to get all movies in XML format");
        long startTime = System.currentTimeMillis();
        List<Movie> movieList = simpleMovieService.getAllMovies();
        String allMoviesJson = jsonManualConverter.allMovieToJson(movieList);
        LOGGER.info("All {} movies received in XML format, it took {} ms", allMoviesJson, System.currentTimeMillis() - startTime);
        return JsonToXmlConverter.jsonToXmlConverter(allMoviesJson);
    }

    @RequestMapping(value = "/movie/{movieId}"
                  , method = RequestMethod.GET
                  , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMovieById(@PathVariable int movieId){
        LOGGER.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        Movie movie = simpleMovieService.getMovieById(movieId);
        String jsonMovie = jsonManualConverter.movieInfoByIdToJson(movie);
        LOGGER.info("Id {} movie received, it took {} ms", movieId  , System.currentTimeMillis() - startTime);
        return jsonMovie;
    }

    @RequestMapping(value = "/movie/{movieId}.xml"
                  , method = RequestMethod.GET
                  , produces = MediaType.APPLICATION_XML_VALUE
                  )
    @ResponseBody
    public String getMovieByIdXML(@PathVariable int movieId){
        LOGGER.info("Sending request to get movie with id = {} in XML format", movieId);
        long startTime = System.currentTimeMillis();
        Movie movie = simpleMovieService.getMovieById(movieId);
        String jsonMovie = jsonManualConverter.movieInfoByIdToJson(movie);
        LOGGER.info("Id {} movie received in XML , it took {} ms", movieId  , System.currentTimeMillis() - startTime);
        return JsonToXmlConverter.jsonToXmlConverter(jsonMovie);
    }
}
