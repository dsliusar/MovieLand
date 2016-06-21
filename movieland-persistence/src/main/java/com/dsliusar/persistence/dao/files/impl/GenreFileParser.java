package com.dsliusar.persistence.dao.files.impl;

import com.dsliusar.persistence.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GenreFileParser {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private Map<String,Genre> genreHashMap = new HashMap<>();



    @Value("${users.genrePath}")
    private String filePath;

    @Autowired
    private CommonFileParser commonFileParser;

    public Map<String,Genre> parseGenreIntoMap() {
        LOGGER.info("Start parsing file with next file path = {}", filePath);
        String fileLine;
        int i = 0;
        try {
            BufferedReader bufReader = commonFileParser.readFromFile(filePath);
            while ((fileLine = bufReader.readLine()) != null) {
                i++;
                Genre genre = new Genre();
                genre.setName(fileLine);
                genre.setGenreId(i);
                genreHashMap.put(fileLine, genre);
            }
        } catch (FileNotFoundException e1) {
            LOGGER.error("Parsing failed with next error {}", String.valueOf(e1));
        } catch (IOException e1) {
            LOGGER.error("Parsing failed with next error {}", String.valueOf(e1));
        }
        LOGGER.info("Parsing file from {} finished successfully", filePath);
        return genreHashMap;
    }


    public Map<String,Genre> getParsedGenresMap() {
        return genreHashMap;
    }


}
