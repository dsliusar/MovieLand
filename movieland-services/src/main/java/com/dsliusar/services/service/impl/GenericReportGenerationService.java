package com.dsliusar.services.service.impl;

import com.dsliusar.persistence.entity.Movie;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class GenericReportGenerationService {

    public static void main(String[] args) throws IOException {
        String filename = "test.pdf";

        PDDocument doc = new PDDocument();
        try

        {
            PDPage page = new PDPage();
            doc.addPage(page);
            String message = "Something";

            Movie movie = new Movie();
            movie.setMovieId(1);
            movie.setPrice(22);

            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream contents = new PDPageContentStream(doc, page);

            contents.beginText();
            contents.setFont(PDType1Font.HELVETICA, 26);
            contents.newLineAtOffset(220, 750);
            contents.showText("ALl movies Report");
            contents.endText();


            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(100, 700);
            contents.showText("movie Id : ");
            contents.endText();

            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(200, 700);
            contents.showText(String.valueOf(movie.getMovieId()));
            contents.endText();

            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(200, 600);
            contents.showText(message);
            contents.endText();

            contents.close();

            doc.save(filename);
        } finally

        {
            doc.close();
        }
    }
}
