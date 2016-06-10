package com.dsliusar.dao.files;

import com.dsliusar.entity.Genre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class GenreFileParser {

    @Value("${users.genrePath}")
    private String filePath;

    private Map<String,Genre> genreHashMap = new HashMap<>();

    public void ParseGenreIntoList() {

        String fileLine;
        int i = 0;
        try {
            BufferedReader bufReader = new CommonFileParser().readFromFile(filePath);
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
