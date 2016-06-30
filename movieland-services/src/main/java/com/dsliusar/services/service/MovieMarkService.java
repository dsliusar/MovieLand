package com.dsliusar.services.service;

public interface MovieMarkService {

    void markMovieToDelete(int movieId);
    void unMarkMovieToDelete(int movieId);
    void deleteMarkedMovies();
}
