package com.dsliusar.dao;

import com.dsliusar.entity.Country;

import java.util.Map;

/**
 * Created by Red1 on 6/8/2016.
 */
public interface CountryDao{

    void insert( Map<String,Country> countryMap);
}
