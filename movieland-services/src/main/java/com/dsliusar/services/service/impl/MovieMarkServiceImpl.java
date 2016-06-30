package com.dsliusar.services.service.impl;

import com.dsliusar.services.service.MovieMarkService;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.tools.exceptions.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class MovieMarkServiceImpl implements MovieMarkService {

    @Autowired
    private MovieService genericMovieService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Set<Integer> markedMovieSet = new CopyOnWriteArraySet<>();

    @Override
    public void markMovieToDelete(int movieId) {
        LOGGER.info("Mark movie for deletion");
        if(markedMovieSet.add(movieId)) {
            LOGGER.info("Movie was marked for deletion");
        } else {
            LOGGER.info("Movie already marked for deletion");
            throw new RequestException("Movie already marked for deletion, movieId = " + movieId);
        }
    }

    @Override
    public void unMarkMovieToDelete(int movieId) {
        LOGGER.info("Un-mark Movie from deletion");
        if (markedMovieSet.remove(movieId)){
            LOGGER.info("Movie successfully un-marked from deletion");
        } else {
            LOGGER.info("Movie id {} cannot be marked because it doesn't exists in Mark collection", movieId);
            throw new RequestException("Movie with ID "+movieId+" cannot be marked, because it doesn't exists");
        }

    }

    @Scheduled(cron = "${service.cronMarkedMovieHouseKeepingInterval}")
    @Override
    public void deleteMarkedMovies() {
        LOGGER.info("Deleting all marked movies");
        int deletedMoviesCount = 0;
        for (Integer movieId : markedMovieSet) {
            genericMovieService.updateCurrentFlag(movieId);
            deletedMoviesCount++;
        }
        markedMovieSet.clear();
        LOGGER.info("All marked movies have been deleted, totally {}", deletedMoviesCount);
    }
}
