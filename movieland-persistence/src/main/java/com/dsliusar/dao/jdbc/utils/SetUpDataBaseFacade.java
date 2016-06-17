package com.dsliusar.dao.jdbc.utils;

import com.dsliusar.constants.Constant;
import com.dsliusar.dao.*;
import com.dsliusar.dao.files.facade.FileParserFacade;
import com.dsliusar.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

public class SetUpDataBaseFacade {


    @Autowired
    private CreateTablesDB createTablesDB;

    @Autowired
    private FileParserFacade fileParserFacade;

    @Autowired
    private UserDao jdbcUserDao;

    @Autowired
    private GenreDao jdbcGenreDao;

    @Autowired
    private MovieDao jdbcMovieDao;

    @Autowired
    private ReviewDao jdbcReviewDao;

    @Autowired
    private CountryDao jdbcCountryDao;

    @SuppressWarnings(value = "unchecked")
    @PostConstruct
    public void init() throws Exception {
        createTablesDB.initialize();
        fileParserFacade.fillMapOfFiles();
        Map<String, Object>  mapOfFiles = fileParserFacade.getMapOfFiles();

        jdbcUserDao.insert((Map<String, User>) mapOfFiles.get(Constant.USER_MAP_NAME));

        jdbcGenreDao.insert((Map<String, Genre>) mapOfFiles.get(Constant.GENRE_MAP_NAME));

        jdbcMovieDao.insert((Map<String, Movie>)mapOfFiles.get(Constant.MOVIE_MAP_NAME)
                           ,(Map<String, Country>)mapOfFiles.get(Constant.COUNTRY_MAP_NAME)
                           ,(Map<String, Genre>)mapOfFiles.get(Constant.GENRE_MAP_NAME));

        jdbcCountryDao.insert((Map<String, Country>) mapOfFiles.get(Constant.COUNTRY_MAP_NAME));
        jdbcReviewDao.insert((List<Review>) mapOfFiles.get(Constant.REVIEW_LIST_NAME));

    }
}
