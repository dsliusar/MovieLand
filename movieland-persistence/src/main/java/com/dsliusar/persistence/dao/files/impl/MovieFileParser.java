package com.dsliusar.persistence.dao.files.impl;

import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.*;

@Component
public class MovieFileParser {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${users.moviePath}")
    private String filePath;

    @Autowired
    private CountryParser countriesParser;

    @Autowired
    private CommonFileParser commonFileParser;

    private Map<String,Movie> movieHashMap = new HashMap<>();

    public Map<String,Movie> parseMoviesIntoList() {
        LOGGER.info("Start parsing file with next file path = {}", filePath);
        int lineCounter = 0;
        int sequenceMovieId = 0;
        String movieName = "";
        String strLine;
        try {
            Movie movie = new Movie();
            BufferedReader bufReader = commonFileParser.readFromFile(filePath);
            while ((strLine = bufReader.readLine()) != null) {

                if (lineCounter == 0) {
                    sequenceMovieId++;
                    movie.setMovieId(sequenceMovieId);
                    lineCounter++;
                }
                if (lineCounter == 1) {
                    movie.setMovieNameRus(strLine.substring(0, strLine.indexOf("/")));
                    movie.setMovieNameOrigin(strLine.substring(strLine.indexOf("/") + 1));
                    movieName = movie.getMovieNameRus();

                } else if (lineCounter == 2) {
                    movie.setYear(Integer.parseInt(strLine));

                } else if (lineCounter == 3) {
                    List<Country> countryList = new ArrayList<>();
                    ArrayList<String> countriesListNames = new ArrayList<>(Arrays.asList(strLine.split(",")));
                    for (String countryName : countriesListNames) {
                        countryName = countryName.trim();
                        countriesParser.saveCountriesIntoList(countryName, sequenceMovieId);
                        Country country = new Country();
                        country.setCountryName(countryName);
                        countryList.add(country);
                    }
                    movie.setCountryList(countryList);

                } else if (lineCounter == 4) {
                   // genreFileParser.fillGenreMovie(strLine, sequenceMovieId);
                    List<Genre> genreList = new ArrayList<>();
                    ArrayList<String> genreOriginNames = new ArrayList<>(Arrays.asList(strLine.split(",")));
                    for (String genreName : genreOriginNames) {
                        genreName = genreName.trim();
                        Genre genre = new Genre();
                        genre.setName(genreName);
                        genreList.add(genre);
                    }
                    movie.setGenreList(genreList);

                } else if (lineCounter == 5) {
                    movie.setDescription(strLine);
                } else if (lineCounter == 6) {
                    movie.setRating(Double.parseDouble(strLine.substring(strLine.indexOf(':') + 1)));
                } else if (lineCounter == 7) {
                    movie.setPrice(Double.parseDouble(strLine.substring(strLine.indexOf(':') + 1)));
                }
                lineCounter++;
                if (strLine.isEmpty() == true) {
                    movieHashMap.put(movieName,movie);
                    movie = new Movie();
                    lineCounter = 0;
                    continue;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Parsing failed with next error {}", e);
        }
        LOGGER.info("Parsing file from {} finished successfully", filePath);
        return movieHashMap;
    }

    public Map<String,Movie> getParsedMovieMap(){
        return movieHashMap;
    }

}
