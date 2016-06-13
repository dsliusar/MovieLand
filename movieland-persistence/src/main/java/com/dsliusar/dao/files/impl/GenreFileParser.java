package com.dsliusar.dao.files.impl;

import com.dsliusar.dao.files.CommonFileParser;
import com.dsliusar.entity.Genre;
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

    @Value("${users.genrePath}")
    private String filePath;


    @Autowired
    private CommonFileParser commonFileParser;

    private Map<String,Genre> genreHashMap = new HashMap<>();

    public void ParseGenreIntoList() {

        String fileLine;
        int i = 0;
        try {
            BufferedReader bufReader = commonFileParser.readFromFile(filePath);
            while ((fileLine = bufReader.readLine()) != null) {
                i++;
                Genre genre = returnGenre();
                genre.setDescprtion(fileLine);
                genre.setGenreId(i);
                genreHashMap.put(fileLine, genre);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static Genre returnGenre() {
        return new Genre();
    }

    public Map<String,Genre> getParsedGenresMap() {
        return genreHashMap;
    }

}
