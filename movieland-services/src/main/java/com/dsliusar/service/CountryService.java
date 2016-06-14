package com.dsliusar.service;

import com.dsliusar.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountriesByMovieId(int movieId);
}
