package com.dsliusar.controller;

import com.dsliusar.enums.SorterValidatorEnum;
import com.dsliusar.service.MovieService;
import com.dsliusar.dto.AllMovieDto;
import com.dsliusar.dto.AllMovieListDto;
import com.dsliusar.dto.MovieByIdDto;
import com.dsliusar.dto.converter.MovieDaoToDto;
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
    private MovieService simpleMovieService;

    @Autowired
    private MovieDaoToDto movieDtoConverter;


    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public AllMovieListDto getAllMoviesXml(
            @RequestParam(value = "rating", required = false) String ratingOrder,
            @RequestParam(value = "price", required = false) String priceOrder) {
        return new AllMovieListDto(getAllMovies(ratingOrder, priceOrder));
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AllMovieDto> getAllMoviesJson(
            @RequestParam(value = "rating", required = false) String ratingOrder,
            @RequestParam(value = "price", required = false) String priceOrder) {
        return getAllMovies(ratingOrder, priceOrder);
    }

    private List<AllMovieDto> getAllMovies(String ratingOrder, String priceOrder) {
        LOGGER.info("Sending request to get all movies");
        long startTime = System.currentTimeMillis();
        List<AllMovieDto> movieDtoList = movieDtoConverter.convertAllMovieToDto(simpleMovieService.getAllMovies(SorterValidatorEnum.vaidateOrderClause(ratingOrder)
                                                                                                               ,SorterValidatorEnum.vaidateOrderClause(priceOrder)));
        LOGGER.info("All {} movies received, it took {} ms", movieDtoList, System.currentTimeMillis() - startTime);
        return movieDtoList;
    }

    @RequestMapping(value = "/movie/{movieId}" ,
                    method=RequestMethod.GET,
                    produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    private ResponseEntity<MovieByIdDto> getMovieById(@PathVariable Integer movieId){
        LOGGER.info("Sending request to get movie with id = {}", movieId);
        if(movieId == null){
            LOGGER.info("Null ID was sent as a request ", movieId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        long startTime = System.currentTimeMillis();
        MovieByIdDto movieByIdDto = movieDtoConverter.convertMovieByIdToDto(simpleMovieService.getMovieById(movieId));
        LOGGER.info("Id {} movie received, it took {} ms", movieByIdDto  , System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieByIdDto, HttpStatus.OK);
    }
}
