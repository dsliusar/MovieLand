package com.dsliusar.service;

import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.service.impl.GenericReviewService;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;

//@ContextConfiguration(locations = "classpath:spring-service-config-test.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class GenericReviewServiceTest {

    @Autowired
    private GenericReviewService genericReviewService;


    public void removeReviewTest(){
        int reviewId = 1;
        Review reviewById = genericReviewService.getReviewById(reviewId);
        System.out.println(reviewById);
    }

    public void userMovieRatingAddTest(){
        MovieRatingChangeRequest movieRatingChangeRequest = new MovieRatingChangeRequest();
        movieRatingChangeRequest.setRating(1.5);
        movieRatingChangeRequest.setUserId(4);
        movieRatingChangeRequest.setMovieId(3);

        try {
            genericReviewService.addRating(movieRatingChangeRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
