package com.app.jmspoc.service.business;

import com.app.jmspoc.dto.YearlyReportDto;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PDFGenerationService {

    private static final Logger log = LoggerFactory.getLogger(PDFGenerationService.class);

    private final TemplateEngine templateEngine;

    public PDFGenerationService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generateAndSavePdf(List<YearlyReportDto> yearlyReports, String fileName) {
        String pdfFilePath = null;
        String pdfOutputDirectory = "src/main/resources/pdfs";

        try {
            Context context = new Context();
            context.setVariable("yearlyReports", yearlyReports);
            String processedHtml = templateEngine.process("pdf/template", context);

            pdfFilePath = Paths.get(pdfOutputDirectory, fileName).toString();

            try (OutputStream outputStream = new FileOutputStream(pdfFilePath)) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(processedHtml);
                renderer.layout();
                renderer.createPDF(outputStream, false);
                renderer.finishPDF();
            }

        } catch (IOException | DocumentException e) {
            log.info("Error generating PDF: " + e.getMessage());
        }

        return pdfFilePath;
    }
}