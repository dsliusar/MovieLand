package com.dsliusar.services.service.impl;

import com.dsliusar.persistence.dao.CurrencyDao;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.services.service.CurrencyService;
import com.dsliusar.tools.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class GenericCurrencyService implements CurrencyService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${service.default.currency:UAH}")
    private String defaultCurrency;

    @Autowired
    private CurrencyDao currencyDao;

    /**
     * Get requested currency and rate for one movie object
     *
     * @param movie
     * @param requestedCurrency
     * @return
     */
    @Override
    public String convertCurrencyToRequested(Movie movie, String requestedCurrency) {
        if (defaultCurrency.equalsIgnoreCase(requestedCurrency) || requestedCurrency == null) {
            return defaultCurrency;
        }
        //set price according to Rate
        movie.setPrice(calculateRate(movie.getPrice(), getRequestedCurrencyRate(requestedCurrency)));
        return requestedCurrency;
    }

    /**
     * Get request currency and rated for list of objects
     *
     * @param movieList
     * @param requestedCurrency
     * @return
     */
    @Override
    public String convertCurrencyToRequested(List<Movie> movieList, String requestedCurrency) {
        if (defaultCurrency.equalsIgnoreCase(requestedCurrency) || requestedCurrency == null) {
            return defaultCurrency;
        }
        //set price according to Rate
        for (Movie movie : movieList) {
            movie.setPrice(calculateRate(movie.getPrice(), getRequestedCurrencyRate(requestedCurrency)));
        }
        return requestedCurrency;
    }

    /**
     * Get requested currency rate from NBU
     *
     * @param requestedCurrency
     * @return
     */
    private Double getRequestedCurrencyRate(String requestedCurrency) {
        LOGGER.info("Get Requested currency Rate, requested currency {} ", requestedCurrency);
        return currencyDao.getSalesRate(requestedCurrency);
    }

    /**
     * Calculate total price
     * DB price is in UAH
     *
     * @param price
     * @param rate
     * @return
     */
    private Double calculateRate(Double price, Double rate) {
        return new BigDecimal(price / rate).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
