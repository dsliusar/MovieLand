package com.dsliusar.services.service.impl;

import com.dsliusar.persistence.entity.Movie;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class GenericPDFReportGenerationServiceDepr {

    private static final PDFont textFont = PDType1Font.HELVETICA_BOLD;
    private static final PDFont headerFont = PDType1Font.HELVETICA;
    private static final int textHeight = 12;
    private static final int headerHeight = 26;
    private static Integer xAxis = 100;
    private static Integer yAxis = 700;
    private static final int headerXAxis = 220;
    private static final int headerYAxis = 750;
    private static final int X_AXIS_SHIFT = 100;

    public static void main(String[] args) {
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setPrice(22);
        movie1.setDescription("AAAKDLASKD");
        movie1.setMovieNameRus("ashfmnvkcvh");
        movie1.setMovieNameOrigin("ashjdashd");

        Movie movie2 = new Movie();
        movie2.setMovieId(111);
        movie2.setPrice(22111);
        movie2.setDescription("111AAAKDLASKD");
        movie2.setMovieNameRus("a1111shfmnvkcvh");
        movie2.setMovieNameOrigin("as111hjdashd");

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        try {
            generateAllSiteMovies(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateAllSiteMovies(List<Movie> movieList) throws IOException {
        String filename = "test.pdf";
        PDDocument doc = new PDDocument();

        try
        {
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contents = new PDPageContentStream(doc, page);
            contents = addHeaderToDocument(contents, headerXAxis, headerYAxis, "All movies Report");

            for (Movie movie : movieList) {
                contents = generateAllMoviesReport(contents, xAxis, yAxis, "Movie ID :", String.valueOf(movie.getMovieId()));
                yAxis = shiftYAxis(yAxis);
                contents = generateAllMoviesReport(contents, xAxis, yAxis, "Title Origin :", movie.getMovieNameOrigin());
                yAxis = shiftYAxis(yAxis);
                contents = generateAllMoviesReport(contents, xAxis, yAxis, "Rus Origin :", movie.getMovieNameRus());
                yAxis = shiftYAxis(yAxis);
                contents = generateAllMoviesReport(contents, xAxis, yAxis, "Movie Description : ", movie.getDescription());
                yAxis = shiftYAxis(yAxis);
                contents = generateAllMoviesReport(contents, xAxis, yAxis, "Genre List : ", String.valueOf(movie.getGenreList()));
                yAxis = shiftYAxis(yAxis);
                contents = generateAllMoviesReport(contents, xAxis, yAxis, "Movie Price : ", String.valueOf(movie.getPrice()));
                yAxis = shiftYAxis(yAxis);
            }
            contents.close();
            doc.save(filename);
        } finally {
            doc.close();
        }
    }

    private static PDPageContentStream generateAllMoviesReport(PDPageContentStream contentStream,
                                                               Integer xAxis,
                                                               int yAxis,
                                                               String fieldName,
                                                               String text
    ) throws IOException {
        if (text != null) {
            contentStream.beginText();
            contentStream.setFont(textFont, textHeight);
            contentStream.newLineAtOffset(xAxis, yAxis);
            contentStream.showText(fieldName);
            contentStream.endText();

            xAxis = shiftXAxis(xAxis,text.length());
            contentStream.beginText();
            contentStream.setFont(textFont, textHeight);
            contentStream.newLineAtOffset(xAxis, yAxis);
            contentStream.showText(text);
            contentStream.endText();
        }
        return contentStream;
    }

    private static PDPageContentStream addHeaderToDocument(PDPageContentStream contentStream,
                                                           int xAxis,
                                                           int yAxis,
                                                           String headerText) throws IOException {
        contentStream.beginText();
        contentStream.setFont(headerFont, headerHeight);
        contentStream.newLineAtOffset(xAxis, yAxis);
        contentStream.showText(headerText);
        contentStream.endText();

        return contentStream;
    }

    private static Integer shiftXAxis(Integer xAxis, Integer textLength) {
        return xAxis + X_AXIS_SHIFT + textLength;
    }

    private static Integer shiftYAxis(Integer yAxis) {
        return yAxis - 20;
    }

}
