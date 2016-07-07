package com.dsliusar.service;

import com.dsliusar.services.service.impl.AbstractPDFReportGenerationService;
import com.dsliusar.services.service.impl.GenericPDFReportGenerationService;
import com.dsliusar.tools.entities.report.AllSiteMovies;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GenericPDFReportServiceGeneratorTest {

    @Test
    public void testPdfGeneration(){
         AllSiteMovies allSiteMovies = new AllSiteMovies();
         java.util.List<AllSiteMovies> allSiteMoviesList = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            allSiteMovies.setMovieId(1);
            allSiteMovies.setMovieNameOrigin("AAAA");
            allSiteMovies.setMovieNameRus("Хорощо");
            allSiteMovies.setDescription("Привет как дела у меня все хорошо");
            allSiteMovies.setGenres("some,some2");
            allSiteMovies.setPrice(11d);
            allSiteMovies.setAddDate(Timestamp.valueOf(LocalDateTime.now()));
            allSiteMovies.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
            allSiteMovies.setRating(9.9d);
            allSiteMovies.setReviewCount(2);
            allSiteMoviesList.add(allSiteMovies);

        }
        AbstractPDFReportGenerationService abstractPDFReportGenerationService = new AbstractPDFReportGenerationService();
        abstractPDFReportGenerationService.createPdfDocument(AllSiteMovies.class, allSiteMoviesList,"D:\\test.pdf","DIma");

    }
}
