package com.app.jmspoc.service.business;

import com.app.jmspoc.dto.EmailDto;
import com.app.jmspoc.model.Account;
import com.app.jmspoc.service.activemq.publisher.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReportGenerationService {

    private static final Logger log = LoggerFactory.getLogger(ReportGenerationService.class);

    private final AccountService accountService;
    private final SaleService saleService;
    private final YearlyReportGenerationPublisher yearlyReportGenerationPublisher;
    private final PDFGenerationService pdfGenerationService;
    private final EmailReportPublisher emailReportPublisher;

    public ReportGenerationService(AccountService accountService,
                                   SaleService saleService,
                                   YearlyReportGenerationPublisher yearlyReportGenerationPublisher,
                                   PDFGenerationService pdfGenerationService,
                                   EmailReportPublisher emailReportPublisher) {
        this.accountService = accountService;
        this.saleService = saleService;
        this.yearlyReportGenerationPublisher = yearlyReportGenerationPublisher;
        this.pdfGenerationService = pdfGenerationService;
        this.emailReportPublisher = emailReportPublisher;
    }

    public void initReportGeneration() {
        log.info("Init report generation");
        yearlyReportGenerationPublisher.publishMessage();
    }

    public void generateYearlyReport() {
        log.info("yearly report generation");
        List<String> reportTexts = new ArrayList<>();
        List<Account> accounts = accountService.findAllAdminAccounts();

        Calendar startCal = Calendar.getInstance();
        startCal.set(2024, Calendar.JANUARY, 1);
        Calendar endCal = Calendar.getInstance();
        endCal.set(2024, Calendar.DECEMBER, 31);

        for (Date month = startCal.getTime(); startCal.before(endCal); startCal.add(Calendar.MONTH, 1), month = startCal.getTime()) {
            startCal.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDayOfMonth = startCal.getTime();

            startCal.set(Calendar.DAY_OF_MONTH, startCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date lastDayOfMonth = startCal.getTime();
            Double totalSale = saleService.sumPricesBetweenDates(firstDayOfMonth, lastDayOfMonth);
            reportTexts.add("date: "+ month +" amount: "+totalSale.toString());
        }

        ByteArrayOutputStream byteArrayOutputStream = pdfGenerationService.generatePdf(reportTexts);

        for (Account account: accounts) {
            EmailDto emailDto = new EmailDto();
            emailDto.setMailTo(account.getEmail());
            emailDto.setMailSubject("Daily Sales Report");
//            emailDto.setContentType("text/plain; charset=\"utf-8\"");
            emailDto.setMailContent("Find attachment below.");
            emailDto.setFileNames(List.of("daily-report.pdf"));
            emailDto.setPdfBytes(byteArrayOutputStream);

            emailReportPublisher.publishMessage(emailDto);
        }
    }
}