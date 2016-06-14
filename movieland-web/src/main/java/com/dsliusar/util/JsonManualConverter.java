package com.dsliusar.util;

import com.dsliusar.Constant;
import com.dsliusar.entity.Movie;
import com.dsliusar.util.adapter.MovieByIdJsonAdapter;
import com.dsliusar.util.adapter.MovieGsonJsonAdapter;
import com.dsliusar.util.converter.JsonToXmlConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonManualConverter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    final GsonBuilder gsonAllMovieBuilder = new GsonBuilder();
    final GsonBuilder gsonMovieByIdBuilder = new GsonBuilder();
    final Gson gsonById;
    final Gson gsonAllMovies;

    {
        gsonAllMovieBuilder.registerTypeAdapter(Movie.class, new MovieGsonJsonAdapter());
        gsonAllMovieBuilder.setPrettyPrinting();
        gsonAllMovies = gsonAllMovieBuilder.create();
    }
    {
        gsonMovieByIdBuilder.registerTypeAdapter(Movie.class, new MovieByIdJsonAdapter());
        gsonMovieByIdBuilder.setPrettyPrinting();
        gsonById = gsonMovieByIdBuilder.create();
    }

    public String allMovieToJson(List<Movie> movieList) {
        LOGGER.info("start converting Movies List to Json");
        long startTime = System.currentTimeMillis();
        String jSonMovies = gsonAllMovies.toJson(movieList);
        LOGGER.info("All movies converted to Json {} format, it took {} ms",jSonMovies, System.currentTimeMillis() - startTime);
        return jSonMovies;
    }

    public String movieInfoByIdToJson(Movie movie){
        LOGGER.info("start converting Movies List to Json");
        long startTime = System.currentTimeMillis();
        String jSonMovieById = gsonById.toJson(movie);
        LOGGER.info("Movie by name {} , we converted to JSON, it took {}", movie.getMovieNameOrigin(), System.currentTimeMillis() - startTime);
        return jSonMovieById;
    }



}
