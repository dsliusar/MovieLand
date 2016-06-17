package com.dsliusar.dto.converter;

import com.dsliusar.dto.AllMovieDto;
import com.dsliusar.dto.MovieByIdDto;
import com.dsliusar.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSliusar on 15.06.2016.
 */

@Service
public class MovieDaoToDto {

    public List<AllMovieDto> convertAllMovieToDto(List<Movie> movieList) {
        List<AllMovieDto> allMovieDtoList = new ArrayList<>();
        for (Movie movie : movieList) {
            AllMovieDto allMovieDto = new AllMovieDto();
            allMovieDto.setGenres(movie.getGenreList());
            allMovieDto.setMovieNameRus(movie.getMovieNameRus());
            allMovieDto.setMovieNameOrigin(movie.getMovieNameOrigin());
            allMovieDto.setRating(movie.getRating());
            allMovieDto.setYear(movie.getYear());
            allMovieDtoList.add(allMovieDto);
        }
        return allMovieDtoList;
    }


    public MovieByIdDto convertMovieByIdToDto(Movie movie) {
        MovieByIdDto movieByIdDto = new MovieByIdDto();
        movieByIdDto.setGenreList(movie.getGenreList());
        movieByIdDto.setMovieNameRus(movie.getMovieNameRus());
        movieByIdDto.setMovieNameOrigin(movie.getMovieNameOrigin());
        movieByIdDto.setRating(movie.getRating());
        movieByIdDto.setYear(movie.getYear());
        movieByIdDto.setCountryList(movie.getCountryList());
        movieByIdDto.setReviewText(movie.getReviewText());
        return movieByIdDto;
    }


}
