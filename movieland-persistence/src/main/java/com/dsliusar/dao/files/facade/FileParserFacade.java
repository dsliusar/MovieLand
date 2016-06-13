package com.dsliusar.dao.files.facade;

import com.dsliusar.Constant;
import com.dsliusar.dao.files.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileParserFacade {

    private Map<String, Object> mapOfFiles = new HashMap();

    @Autowired
    UserFileParser userFileParser;

    @Autowired
    GenreFileParser genreFileParser;

    @Autowired
    MovieFileParser movieFileParser;

    @Autowired
    CountryParser countryParser;

    @Autowired
    ReviewFileParser reviewFileParser;


    public void fillMapOfFiles(){
        mapOfFiles.put(Constant.USER_MAP_NAME, userFileParser.ParseUsersIntoList());
        mapOfFiles.put(Constant.GENRE_MAP_NAME, genreFileParser.ParseGenreIntoMap());
        mapOfFiles.put(Constant.MOVIE_MAP_NAME, movieFileParser.parseMoviesIntoList());
        mapOfFiles.put(Constant.COUNTRY_MAP_NAME, countryParser.getCountryMap());
        mapOfFiles.put(Constant.REVIEW_LIST_NAME, reviewFileParser.parseReviewIntoList());

    }

    public Map<String, Object> getMapOfFiles(){
        return mapOfFiles;
    }

}
