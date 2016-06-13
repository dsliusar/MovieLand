package com.dsliusar.dao.files.impl;

import com.dsliusar.entity.Movie;
import com.dsliusar.entity.Review;
import com.dsliusar.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ReviewFileParser {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${users.reviewPath}")
    private String filePath;

    @Autowired
    private MovieFileParser movieFileParser;

    @Autowired
    private UserFileParser userFileParser;

    @Autowired
    private CommonFileParser commonFileParser;

    private static int NOT_FOUND_VALUE = -1;
    private List<Review> reviewList = new ArrayList<Review>();
    private Map<String,Movie> movieMap;
    private Map<String,User> userMap;

    public List<Review> parseReviewIntoList() {
        LOGGER.info("Start parsing file with next file path = {}", filePath);
        String fileLine;
        int counter = 0;
        int sequenceReview = 0;
        movieMap = movieFileParser.getParsedMovieMap();// get movie List
        userMap = userFileParser.getParsedUserMap(); // get users list
        Review review = new Review();
        try {
            BufferedReader bufReader = commonFileParser.readFromFile(filePath);
            while ((fileLine = bufReader.readLine()) != null) {
                if (counter == 0) {
                    sequenceReview++;
                    review.setReviewId(sequenceReview);
                    counter++;
                }
                if (counter == 1) {
                    review.setMovieId(getMovieIdByName(fileLine));
                } else if (counter == 2) {
                    review.setUserId(getUserIdByName(fileLine));
                } else if (counter == 3) {
                    review.setReviewText(fileLine);
                }
                counter++;

                if (fileLine.isEmpty() == true) {
                    reviewList.add(review);
                    review = new Review();
                    counter = 0;
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Parsing failed with next error {}", String.valueOf(e));
        } catch (IOException e) {
            LOGGER.error("Parsing failed with next error {}", String.valueOf(e));
        }
        LOGGER.info("Parsing file from {} finished successfully", filePath);
        return reviewList;
    }

    private int getUserIdByName(String userName) {
        if (userMap.containsKey(userName)){
                return userMap.get(userName).getUserId();
            }
        return NOT_FOUND_VALUE++;
    }

    private int getMovieIdByName(String movieName) {
        if (movieMap.containsKey(movieName)){
                return movieMap.get(movieName).getMovieId();
            }
        return NOT_FOUND_VALUE++;
    }

    public List<Review> reviewList() {
        return reviewList;
    }

    public void setMovieFileParser(MovieFileParser movieFileParser) {
        this.movieFileParser = movieFileParser;
    }

    public void setUserFileParser(UserFileParser userFileParser) {
        this.userFileParser = userFileParser;
    }

}
