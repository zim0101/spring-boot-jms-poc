package com.app.jmspoc.service.activemq.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DailySalesReportGenerationSubscriber {

    private static final Logger log = LoggerFactory.getLogger(DailySalesReportGenerationSubscriber.class);

    @JmsListener(destination = "${jmspoc.queue.daily-sales-report-generation}")
    public void subscribeMessage() {
        log.info("DailySalesReportGenerationSubscriber received");
    }
}