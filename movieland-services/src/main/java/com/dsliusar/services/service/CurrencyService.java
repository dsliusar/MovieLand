package com.dsliusar.services.service;


import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.CurrencyEntity;

import java.util.List;

public interface CurrencyService {

    CurrencyEntity[] getNbuRates();

    String convertCurrencyToRequested(Movie movie,String requestedCurrency);

    String convertCurrencyToRequested(List<Movie> movieList, String requestedCurrency);




}
