package com.app.jmspoc.service.activemq.subscriber;

import com.app.jmspoc.service.business.ReportGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class YearlySalesReportGenerationSubscriber {

    private static final Logger log = LoggerFactory.getLogger(YearlySalesReportGenerationSubscriber.class);
    private final ReportGenerationService reportGenerationService;

    public YearlySalesReportGenerationSubscriber(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @JmsListener(destination = "${jmspoc.queue.yearly-sales-report-generation}")
    public void subscribeMessage() {
        log.info("YearlySalesReportGenerationSubscriber received");
        reportGenerationService.generateAndSendYearlyReport();
    }
}