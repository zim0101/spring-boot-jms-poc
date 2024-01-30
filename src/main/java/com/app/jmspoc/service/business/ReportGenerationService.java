package com.app.jmspoc.service.business;

import com.app.jmspoc.dto.EmailDto;
import com.app.jmspoc.dto.YearlyReportDto;
import com.app.jmspoc.model.Account;
import com.app.jmspoc.service.activemq.publisher.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void generateAndSendYearlyReport() {
        log.info("yearly report generation");
        String reportFileName = "report.pdf";
        List<YearlyReportDto> yearlyReports = new ArrayList<>();

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
            YearlyReportDto yearlyReportDto = new YearlyReportDto();
            yearlyReportDto.setMonth(month.toString());
            yearlyReportDto.setSale(totalSale.toString());
            yearlyReports.add(yearlyReportDto);
        }

        String pdfFilePath = pdfGenerationService.generateAndSavePdf(yearlyReports, reportFileName);
        sendEmailsToAllAdmin(pdfFilePath);
    }

    public void sendEmailsToAllAdmin(String pdfFilePath) {
        List<Account> accounts = accountService.findAllAdminAccounts();

        for (Account account: accounts) {
            EmailDto emailDto = new EmailDto();
            emailDto.setMailTo(account.getEmail());
            emailDto.setMailSubject("Daily Sales Report");
            emailDto.setMailContent("Find attachment below.");
            emailDto.setFileNames(List.of(pdfFilePath));

            emailReportPublisher.publishMessage(emailDto);
        }
    }
}