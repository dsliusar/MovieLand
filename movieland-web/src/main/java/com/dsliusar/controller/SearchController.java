package com.dsliusar.controller;

import com.dsliusar.util.dto.AllMovieListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by DSliusar on 14.06.2016.
 */
@RestController
@RequestMapping(value = "/v1")
public class SearchController {

    @RequestMapping(value = "/search"
                   ,method = RequestMethod.POST
                   ,consumes = MediaType.APPLICATION_JSON_VALUE
                   ,produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AllMovieListDto>> searchMovie(@RequestBody String json){
        System.out.println(json);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
