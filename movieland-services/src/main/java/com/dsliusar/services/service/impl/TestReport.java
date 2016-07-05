package com.dsliusar.services.service.impl;
import java.io.FileInputStream;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class TestReport {

    public static void main(String[] args) throws Exception{

        FileInputStream excelDocument = new FileInputStream(new File("1.xls"));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelDocument);
        HSSFSheet xlsWorkSheet = hssfWorkbook.getSheetAt(0);

        // To iterate over the rows
        Iterator<Row> rowIterator = xlsWorkSheet.iterator();

        //Create PDF document
        Document finalPdfDocument = new Document();
        PdfWriter.getInstance(finalPdfDocument, new FileOutputStream("Excel2PDF_Output.pdf"));
        finalPdfDocument.open();

        PdfPTable pdfPTable = new PdfPTable(2);
        PdfPCell tableCell;

        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch(cell.getCellType()) {
                    case Cell.CELL_TYPE_BLANK:
                        pdfPTable.spacingAfter();
                        break;
                    case Cell.CELL_TYPE_STRING:
                        tableCell=new PdfPCell(new Phrase(cell.getStringCellValue()));
                        pdfPTable.addCell(tableCell);
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        tableCell=new PdfPCell(new Phrase(String.valueOf(cell.getNumericCellValue())));
                        pdfPTable.addCell(tableCell);
                        break;
                }
            }
        }

        finalPdfDocument.add(pdfPTable);
        finalPdfDocument.close();

        excelDocument.close();
    }


}
