package com.dsliusar.services.service.impl;

import com.dsliusar.services.service.MovieService;
import com.dsliusar.services.service.ReportGenerationService;
import com.dsliusar.tools.entities.report.AllSiteMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericPDFReportGenerationService extends AbstractPDFReportGenerationService implements ReportGenerationService {

    @Value("${service.allSiteMoviesFileName.pdf}")
    private String allSiteMoviesFileName;

    @Autowired
    private MovieService genericMovieService;

    @Override
    public void generateAllSiteMovieReport(String userName) {
        List<AllSiteMovies> allSiteMoviesList = genericMovieService.getAllSiteMovies();
        createPdfDocument(AllSiteMovies.class, allSiteMoviesList, allSiteMoviesFileName, userName);
    }



}
