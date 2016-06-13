package com.dsliusar.dao.jdbc.utils;

import com.dsliusar.dao.*;
import com.dsliusar.dao.files.facade.FileParserFacade;
import com.dsliusar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.dsliusar.Constant;
import javax.annotation.PostConstruct;
import java.util.Map;

public class SetUpDB {

    @Autowired
    private CreateTablesDB createTablesDB;

    @Autowired
    private UserDao jdbcUserDao;

    @Autowired
    private GenreDao jdbcGenreDao;

    @Autowired
    private GenreMovieDao jdbcGenreMovieDao;

    @Autowired
    private MovieDao jdbcMovieDao;

    @Autowired
    private ReviewDao jdbcReviewDao;

    @Autowired
    private CountryDao jdbcCountryDao;

    @Autowired
    private CountryMovieDao jdbcCountryMovieDao;

    @Autowired
    private FileParserFacade fileParserFacade;

    private Map<String, Object> mapOfFiles;

    @PostConstruct
    public void init() throws Exception {
        createTablesDB.initialize();
        fileParserFacade.fillMapOfFiles();
        mapOfFiles = fileParserFacade.getMapOfFiles();
        jdbcUserDao.insert((Map<String, User>) mapOfFiles.get(Constant.USER_MAP_NAME));
        jdbcGenreDao.insert();
        jdbcMovieDao.insert();
        jdbcGenreMovieDao.insert();
        jdbcReviewDao.insert();
        jdbcCountryDao.insert();
        jdbcCountryMovieDao.insert();
    }
}
