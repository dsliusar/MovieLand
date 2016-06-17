package com.dsliusar.controller;

import com.dsliusar.dto.MovieSearchRequestDto;
import com.dsliusar.service.MovieService;
import com.dsliusar.util.dto.AllMovieDto;
import com.dsliusar.util.dto.AllMovieListDto;
import com.dsliusar.util.dto.converter.MovieDaoToDto;
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
public class SearchController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieDaoToDto movieDtoConverter;

    public ResponseEntity<List<AllMovieDto>> searchMovies(MovieSearchRequestDto searchMovieDto){
        LOGGER.info(" Send request to search movies by criteria {} ", searchMovieDto);
        List<AllMovieDto> movieDtoList;
        try {
           movieDtoList = movieDtoConverter.convertAllMovieToDto(movieService.getAllSearchedMovies(searchMovieDto));
        } catch (Exception e){
             LOGGER.error("Error happened in search movie controller ", e);
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (movieDtoList.isEmpty()){
            LOGGER.info("Null content returned");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info(" Movies found by search criteria {} ", searchMovieDto);
        return new ResponseEntity<>(movieDtoList,HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces=MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public AllMovieListDto searchMoviesXML(@RequestBody MovieSearchRequestDto searchMovieDto) {
        return new AllMovieListDto(searchMovies(searchMovieDto));
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<AllMovieDto>> searchMoviesJSON(@RequestBody MovieSearchRequestDto searchMovieDto) {
        return searchMovies(searchMovieDto);
    }

}
