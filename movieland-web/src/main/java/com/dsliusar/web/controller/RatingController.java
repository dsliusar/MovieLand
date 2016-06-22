package com.dsliusar.web.controller;

import com.dsliusar.http.entities.MovieRatingChangeRequest;
import com.dsliusar.http.entities.MovieRatingOnChangeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class RatingController {

    @RequestMapping(value = "/rating", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieRatingOnChangeResponse> changeMovieRating(@RequestBody MovieRatingChangeRequest movieRatingChange,
                                                                         @RequestHeader(value = "security-token") String token)  {
        MovieRatingOnChangeResponse movieRatingOnChangeResponse = new MovieRatingOnChangeResponse();
        return new ResponseEntity<>(movieRatingOnChangeResponse, HttpStatus.OK);
    }
}
