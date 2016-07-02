package com.dsliusar.services.service.impl;

import com.dsliusar.persistence.dao.CurrencyDao;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.services.service.CurrencyService;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.exceptions.UrlRequestException;
import com.dsliusar.tools.http.entities.CurrencyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GenericCurrencyService implements CurrencyService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final static String DEFAULT_CURRENCY = "UAH";

    @Autowired
    private CurrencyDao currencyDao;

    /**
     * Get NBU rate
     * @return
     */
    @Override
    public CurrencyEntity[] getNbuRates() {
        try {
            return currencyDao.getNbuRates();
        } catch (IOException e) {
            throw new UrlRequestException("Exception during getting request from NBU");
        }
    }

    /**
     * Get requested currency rate from NBU
     * @param requestedCurrency
     * @param currencyEntities
     * @return
     */
    private Double getRequestedCurrencyRate(String requestedCurrency,CurrencyEntity[] currencyEntities) {
        LOGGER.info("Get Requested currency Rate");
        for (CurrencyEntity currencyEntity : currencyEntities) {
            if (currencyEntity.getExchange_currency().equalsIgnoreCase(requestedCurrency)) {
                return currencyEntity.getSale();
            }
        }
        throw new NotFoundException("Requested currency " + requestedCurrency + " is not supported by application");

    }

    /**
     * Get requested currency and rate for one movie object
     * @param movie
     * @param requestedCurrency
     * @return
     */
    @Override
    public String convertCurrencyToRequested(Movie movie, String requestedCurrency) {
        if (DEFAULT_CURRENCY.equalsIgnoreCase(requestedCurrency) || requestedCurrency == null) {
            return DEFAULT_CURRENCY;
        }
        //get NBU rate
        CurrencyEntity[] currencyEntities = getNbuRates();

        //set price according to Rate
        movie.setPrice(movie.getPrice() * getRequestedCurrencyRate(requestedCurrency,currencyEntities));
        return requestedCurrency;
    }


    /**
     * Get request currency and rated for list of objects
     * @param movieList
     * @param requestedCurrency
     * @return
     */
    @Override
    public String convertCurrencyToRequested(List<Movie> movieList, String requestedCurrency) {
        if (DEFAULT_CURRENCY.equalsIgnoreCase(requestedCurrency) || requestedCurrency == null) {
            return DEFAULT_CURRENCY;
        }
        //get NBU rate
        CurrencyEntity[] currencyEntities = getNbuRates();

        //set price according to Rate
        for (Movie movie : movieList) {
            movie.setPrice(movie.getPrice() * getRequestedCurrencyRate(requestedCurrency,currencyEntities));
        }
        return requestedCurrency;
    }
}
