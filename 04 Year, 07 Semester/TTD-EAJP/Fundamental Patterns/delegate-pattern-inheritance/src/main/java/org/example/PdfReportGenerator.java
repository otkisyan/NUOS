package org.example;

public class PdfReportGenerator {
    ReportGenerator reportGenerator;

    public PdfReportGenerator() {
        this.reportGenerator = new ReportGenerator();
    }

    public void generateBody() {
        reportGenerator.generateBody();
    }
}