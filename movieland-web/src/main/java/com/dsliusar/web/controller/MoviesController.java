package com.dsliusar.web.controller;

import com.dsliusar.tools.annotations.SecurityRolesAllowed;
import com.dsliusar.tools.enums.Roles;
import com.dsliusar.tools.http.entities.MovieAddRequest;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.MovieSortRequest;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.web.dto.movies.AllMovieDto;
import com.dsliusar.web.dto.movies.AllMovieListDto;
import com.dsliusar.web.dto.movies.MovieByIdDto;
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

    @SecurityRolesAllowed(roles = {Roles.GUEST})
    @RequestMapping(value = "/movies",
                    method = RequestMethod.GET,
                    produces=MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public AllMovieListDto getAllMoviesXml(MovieSortRequest movieSortRequest) {
        return new AllMovieListDto(getAllMovies(movieSortRequest));
    }

    @SecurityRolesAllowed(roles = {Roles.GUEST})
    @RequestMapping(value = "/movies",
                    method = RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AllMovieDto> getAllMoviesJson(MovieSortRequest movieSortRequest) {
        return getAllMovies(movieSortRequest);
    }

    @SecurityRolesAllowed(roles = {Roles.GUEST})
    @RequestMapping(value = "/movie/{movieId}" ,
                    method=RequestMethod.GET,
                    produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<MovieByIdDto> getMovieById(@PathVariable Integer movieId){
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

//    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/add" ,
            method=RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addMovie(@RequestBody MovieAddRequest movieAddRequest,
                                      @RequestHeader(value = "security-token") String token){
        System.out.println(movieAddRequest);
        return new ResponseEntity<>("Movie were added successfully", HttpStatus.OK);
    }

    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/update" ,
            method=RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateMovie(@RequestBody MovieRatingChangeRequest movieRatingChangeRequest,
                                         @RequestHeader(value = "security-token") String token){
        return new ResponseEntity<>("Movie were added successfully", HttpStatus.OK);
    }

    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/mark/{movieId}" ,
            method=RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> markMovie(@PathVariable Integer movieId){
        return new ResponseEntity<>("Movie were added successfully", HttpStatus.OK);
    }

    /**
     * Unmark movie.
     * If movie was marked for deletion than un-mark it.
     * @param movieId
     * @return
     */
    @SecurityRolesAllowed(roles = {Roles.ADMIN})
    @RequestMapping(value = "/movies/unmark/{movieId}" ,
            method=RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> unMarkMovie(@PathVariable Integer movieId){
        return new ResponseEntity<>("Movie were added successfully", HttpStatus.OK);
    }


    private List<AllMovieDto> getAllMovies(MovieSortRequest movieSortRequest) {
        LOGGER.info("Sending request to get all movies");
        long startTime = System.currentTimeMillis();
        List<AllMovieDto> movieDtoList = movieToDtoTransformer.transformAllMovieToDto(
                genericMovieService.getAllMovies(movieSortRequest));

        LOGGER.info("All movies received, it took {} ms", System.currentTimeMillis() - startTime);
        return movieDtoList;
    }
}
