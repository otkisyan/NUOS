package org.example;

public class Main {
    public static void main(String[] args) {
        PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
        pdfReportGenerator.generateBody();
        // pdfReportGenerator.generateFooter(); // Cannot resolve method 'generateFooter' in 'PdfReportGenerator'
    }
}