package com.dsliusar.dao.files;

import com.dsliusar.entity.Genre;
import com.dsliusar.entity.GenreMovie;
import com.dsliusar.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Component
public class MovieFileParser {

    @Autowired
    private GenreFileParser genreFileParser;

    @Autowired
    private CountryParser countriesParser;

    @Value("${users.moviePath}")
    private String filePath;

    private List<GenreMovie> genreMoviesList = new ArrayList<GenreMovie>();

    private Map<String,Movie> movieHashMap = new HashMap<>();

    private  void fillGenreMovie(String genreName, int movieId) {
        ArrayList<String> genreNamesList = new ArrayList<>(Arrays.asList(genreName.split(",")));
        Map<String,Genre> genresParsedMap = genreFileParser.getParsedGenresMap();
        for (String str : genreNamesList) {
             str = str.trim();
             if (genresParsedMap.containsKey(str)) {
                    genreMoviesList.add(populateGenreMovieId(genresParsedMap.get(str).getGenreId(), movieId));

            }
        }
    }

    private GenreMovie populateGenreMovieId(int inGenreId, int movieId) {
        GenreMovie genreMovie = new GenreMovie();
        genreMovie.setMovieId(movieId);
        genreMovie.setGenreId(inGenreId);
        return genreMovie;
    }

    public void ParseMoviesIntoList() {
        int lineCounter = 0;
        int sequenceMovieId = 0;
        String movieName = "";
        String strLine;
        try {
            Movie movie = getNewMovie();
            BufferedReader bufReader = new CommonFileParser().readFromFile(filePath);
            while ((strLine = bufReader.readLine()) != null) {

                if (lineCounter == 0) {
                    sequenceMovieId++;
                    movie.setMovieId(sequenceMovieId);
                    lineCounter++;
                }
                if (lineCounter == 1) {
                    movie.setMovieNameRus(strLine.substring(0, strLine.indexOf("/")));
                    movie.setMovieNameeEng(strLine.substring(strLine.indexOf("/") + 1));
                    movieName = movie.getMovieNameRus();
                } else if (lineCounter == 2) {
                    movie.setYear(Integer.parseInt(strLine));
                } else if (lineCounter == 3) {
                    countriesParser.saveCountriesIntoList(strLine, sequenceMovieId);
                } else if (lineCounter == 4) {
                    fillGenreMovie(strLine, sequenceMovieId);
                } else if (lineCounter == 5) {
                    movie.setDesciprtion(strLine);
                } else if (lineCounter == 6) {
                    movie.setRating(Double.parseDouble(strLine.substring(strLine.indexOf(':') + 1)));
                } else if (lineCounter == 7) {
                    movie.setPrice(Double.parseDouble(strLine.substring(strLine.indexOf(':') + 1)));
                }
                lineCounter++;
                if (strLine.isEmpty() == true) {
                    movieHashMap.put(movieName,movie);
                    movie = getNewMovie();
                    lineCounter = 0;
                    continue;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GenreMovie> getGenreMoviesList(){
        return genreMoviesList;
    }

    public void setGenreFileParser(GenreFileParser genreFileParser) {
        this.genreFileParser = genreFileParser;
    }

    public Map<String,Movie> getParsedMovieMap(){
        return movieHashMap;
    }

    private Map<String,Genre>  returnGenreList(){
        return genreFileParser.getParsedGenresMap();
    }

    private static Movie getNewMovie() {
        return new Movie();
    }

    public void setCountriesParser(CountryParser countriesParser) { this.countriesParser = countriesParser; }


}
