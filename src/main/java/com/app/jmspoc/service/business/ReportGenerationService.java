package com.app.jmspoc.service.business;

import com.app.jmspoc.service.activemq.publisher.*;
import com.app.jmspoc.service.activemq.subscriber.DailySalesReportGenerationSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ReportGenerationService {

    private static final Logger log = LoggerFactory.getLogger(ReportGenerationService.class);

    private final SaleService saleService;
    private final DailyReportGenerationPublisher dailyReportGenerationPublisher;
    private final WeeklyReportGenerationPublisher weeklyReportGenerationPublisher;
    private final MonthlyReportGenerationPublisher monthlyReportGenerationPublisher;
    private final YearlyReportGenerationPublisher yearlyReportGenerationPublisher;
    private final EmailReportPublisher emailReportPublisher;

    public ReportGenerationService(SaleService saleService,
                                   DailyReportGenerationPublisher dailyReportGenerationPublisher,
                                   WeeklyReportGenerationPublisher weeklyReportGenerationPublisher,
                                   MonthlyReportGenerationPublisher monthlyReportGenerationPublisher,
                                   YearlyReportGenerationPublisher yearlyReportGenerationPublisher,
                                   EmailReportPublisher emailReportPublisher) {
        this.saleService = saleService;
        this.dailyReportGenerationPublisher = dailyReportGenerationPublisher;
        this.weeklyReportGenerationPublisher = weeklyReportGenerationPublisher;
        this.monthlyReportGenerationPublisher = monthlyReportGenerationPublisher;
        this.yearlyReportGenerationPublisher = yearlyReportGenerationPublisher;
        this.emailReportPublisher = emailReportPublisher;
    }

    public void initReportGeneration() {
        log.info("Init report generation");
        dailyReportGenerationPublisher.publishMessage();
        weeklyReportGenerationPublisher.publishMessage();
        monthlyReportGenerationPublisher.publishMessage();
        yearlyReportGenerationPublisher.publishMessage();
    }

    public void generateDailyReport() {
        log.info("daily report generation");
        emailReportPublisher.publishMessage();
    }

    public void generateWeeklyReport() {
        log.info("weekly report generation");
        emailReportPublisher.publishMessage();
    }

    public void generateMonthlyReport() {
        log.info("monthly report generation");
        emailReportPublisher.publishMessage();
    }

    public void generateYearlyReport() {
        log.info("yearly report generation");
        emailReportPublisher.publishMessage();
    }
}