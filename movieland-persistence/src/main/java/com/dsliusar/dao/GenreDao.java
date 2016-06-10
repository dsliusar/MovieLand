package com.dsliusar.dao;

import java.util.Map;

public interface GenreDao extends CommonDao {

    void insert();
    Map<Integer,String> getAllGenres();

}
