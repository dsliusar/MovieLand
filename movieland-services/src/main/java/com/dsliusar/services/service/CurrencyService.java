package com.dsliusar.services.service;


import com.dsliusar.persistence.entity.Movie;

import java.util.List;

public interface CurrencyService {

    String convertCurrencyToRequested(Movie movie,String requestedCurrency);

    String convertCurrencyToRequested(List<Movie> movieList, String requestedCurrency);




}
