package com.dsliusar.controller;

import com.dsliusar.service.MovieService;
import com.dsliusar.util.dto.AllMovieDto;
import com.dsliusar.util.dto.AllMovieListDto;
import com.dsliusar.util.dto.SearchMovieDto;
import com.dsliusar.util.dto.converter.MovieDaoToDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.util.List;

/**
 * Created by DSliusar on 14.06.2016.
 */
@RestController
@RequestMapping(value = "/v1")
public class SearchController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    MovieService movieService;

    @Autowired
    MovieDaoToDto movieDtoConverter;

    @RequestMapping(value = "/search"
                   ,method = RequestMethod.POST
                   ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchMovie(@RequestBody SearchMovieDto searchMovieDto){
        List<AllMovieDto> movieDtoList = movieDtoConverter.convertAllMovieToDto(movieService.getAllSearchedMovies(searchMovieDto.getMovieNameRus(), searchMovieDto.getMovieNameOrigin()
             , searchMovieDto.getCountry(), searchMovieDto.getYear(), searchMovieDto.getGenreName()));
        System.out.println(movieDtoList);
        return new ResponseEntity<>("dasdasd",HttpStatus.OK);
    }
}
