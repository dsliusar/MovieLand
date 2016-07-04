package com.dsliusar.web.controller;

import com.dsliusar.services.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class PosterController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService genericMovieService;

    @RequestMapping(value = "/poster/{movieId}",
            method = RequestMethod.GET,
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getMoviePosterById(@PathVariable Integer movieId) {
        LOGGER.info("Sending request to get poster for movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        byte[] moviePoster = genericMovieService.getMoviePoster(movieId);
        LOGGER.info("Movie poster successfully send to client, it took {}", System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(moviePoster, HttpStatus.OK);
    }
}
