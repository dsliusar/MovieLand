package com.dsliusar.web.controller;

import com.dsliusar.tools.http.entities.MovieSortRequest;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.web.dto.AllMovieDto;
import com.dsliusar.web.dto.AllMovieListDto;
import com.dsliusar.web.dto.MovieByIdDto;
import com.dsliusar.web.dto.converter.MovieToDtoTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class MoviesController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService genericMovieService;

    @Autowired
    private MovieToDtoTransformer movieToDtoTransformer;


    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public AllMovieListDto getAllMoviesXml(MovieSortRequest movieSortRequest) {
        return new AllMovieListDto(getAllMovies(movieSortRequest));
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AllMovieDto> getAllMoviesJson(MovieSortRequest movieSortRequest) {
        return getAllMovies(movieSortRequest);
    }

    private List<AllMovieDto> getAllMovies(MovieSortRequest movieSortRequest) {
        LOGGER.info("Sending request to get all movies");
        long startTime = System.currentTimeMillis();
        List<AllMovieDto> movieDtoList = movieToDtoTransformer.transformAllMovieToDto(
                genericMovieService.getAllMovies(movieSortRequest));

        LOGGER.info("All movies received, it took {} ms", System.currentTimeMillis() - startTime);
        return movieDtoList;
    }

    @RequestMapping(value = "/movie/{movieId}" ,
                    method=RequestMethod.GET,
                    produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    private ResponseEntity<MovieByIdDto> getMovieById(@PathVariable Integer movieId){
        LOGGER.info("Sending request to get movie with id = {}", movieId);
        if(movieId == null){
            LOGGER.info("Null ID was sent as a request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        long startTime = System.currentTimeMillis();
        MovieByIdDto movieByIdDto = movieToDtoTransformer.transformMovieByIdToDto(genericMovieService.getMovieById(movieId));
        LOGGER.info("movie received, it took {} ms", System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieByIdDto, HttpStatus.OK);
    }
}
