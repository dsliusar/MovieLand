package com.dsliusar.util;

import com.dsliusar.entity.Movie;
import com.dsliusar.util.adapter.MovieByIdJsonAdapter;
import com.dsliusar.util.adapter.MovieGsonJsonAdapter;
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
    {
        gsonAllMovieBuilder.registerTypeAdapter(Movie.class, new MovieGsonJsonAdapter());
        gsonAllMovieBuilder.setPrettyPrinting();
    }
    {
        gsonMovieByIdBuilder.registerTypeAdapter(Movie.class, new MovieByIdJsonAdapter());
        gsonMovieByIdBuilder.setPrettyPrinting();
    }

    public String allMovieToJson(List<Movie> movieList) {
        LOGGER.info("start converting Movies List to Json");
        long startTime = System.currentTimeMillis();
        Gson gson = gsonAllMovieBuilder.create();
        String jSonMovies = gson.toJson(movieList);
        LOGGER.info("All movies converted to Json {} format, it took {} ms",jSonMovies, System.currentTimeMillis() - startTime);
        return jSonMovies;
    }

    public String movieInfoByIdToJson(Movie movie){
        LOGGER.info("start converting Movies List to Json");
        long startTime = System.currentTimeMillis();
        Gson gson = gsonMovieByIdBuilder.create();
        String jSonMovieById = gson.toJson(movie);
        LOGGER.info("Movie by name {} , we converted to JSON, it took {}", movie.getMovieNameeEng(), System.currentTimeMillis() - startTime);
        return jSonMovieById;
    }



}
