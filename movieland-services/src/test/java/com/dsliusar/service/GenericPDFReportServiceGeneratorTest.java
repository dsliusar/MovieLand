package com.dsliusar.service;

import com.dsliusar.services.service.impl.GenericPDFReportGenerationService;
import com.dsliusar.tools.entities.report.AllSiteMovies;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by DSliusar on 05.07.2016.
 */
public class GenericPDFReportServiceGeneratorTest {

    public void testPdfGeneration(){
        //new GenericPDFReportService().createPdfDocument();
        AllSiteMovies allSiteMovies = new AllSiteMovies();
        allSiteMovies.setMovieId(1);
        allSiteMovies.setMovieNameOrigin("AAAA");
        allSiteMovies.setMovieNameRus("aaaa");
        allSiteMovies.setDescription("GOOD MOVIE REALY AMAZING I LIKE IT SO MUCH");
        allSiteMovies.setGenres("some,some2");
        allSiteMovies.setPrice(11d);

        allSiteMovies.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        allSiteMovies.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        allSiteMovies.setRating(9.9d);
        allSiteMovies.setReviewCount(2);

        AllSiteMovies allSiteMovies1 = new AllSiteMovies();
        allSiteMovies1.setMovieId(1);
        allSiteMovies1.setMovieNameOrigin("AAAA");
        allSiteMovies1.setMovieNameRus("aaaa");
        allSiteMovies1.setDescription("GOOD MOVIE REALY AMAZING I LIKE IT SO MUCH");
        allSiteMovies1.setGenres("some,some2");
        allSiteMovies1.setPrice(11d);

        allSiteMovies1.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        allSiteMovies1.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        allSiteMovies1.setRating(9.9d);
        allSiteMovies1.setReviewCount(2);

        java.util.List<AllSiteMovies> allSiteMoviesList = new ArrayList<>();
        allSiteMoviesList.add(allSiteMovies);
        allSiteMoviesList.add(allSiteMovies1);

        allSiteMovies1.setMovieId(1);
        allSiteMovies1.setMovieNameOrigin("AAAA");
        allSiteMovies1.setMovieNameRus("aaaa");
        allSiteMovies1.setDescription("GOOD MOVIE REALY AMAZING I LIKE IT SO MUCH");
        allSiteMovies1.setGenres("some,some2");
        allSiteMovies1.setPrice(11d);
        allSiteMovies1.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
        allSiteMovies1.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        allSiteMovies1.setRating(9.9d);
        allSiteMovies1.setReviewCount(2);

        allSiteMoviesList.add(allSiteMovies1);

        GenericPDFReportGenerationService genericPDFReportService = new GenericPDFReportGenerationService();
//        genericPDFReportService.createPdfDocument(AllSiteMovies.class, allSiteMoviesList);

    }
}
