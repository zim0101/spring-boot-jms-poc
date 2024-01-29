package com.app.jmspoc.service.business;

import com.app.jmspoc.service.email.EmailService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PDFGenerationService {

    private static final Logger log = LoggerFactory.getLogger(PDFGenerationService.class);

    public ByteArrayOutputStream generatePdf(List<String> lines) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            for (String line: lines) {
                document.add(new Paragraph(line));
            }
            document.close();
        } catch (DocumentException e) {
            log.error(e.getLocalizedMessage());
        }

        return byteArrayOutputStream;
    }
}