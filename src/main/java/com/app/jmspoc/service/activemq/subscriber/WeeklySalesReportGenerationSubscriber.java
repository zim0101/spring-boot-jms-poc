package com.app.jmspoc.service.activemq.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class WeeklySalesReportGenerationSubscriber {

    private static final Logger log = LoggerFactory.getLogger(WeeklySalesReportGenerationSubscriber.class);

    @JmsListener(destination = "${jmspoc.queue.weekly-sales-report-generation}")
    public void subscribeMessage() {
        log.info("WeeklySalesReportGenerationSubscriber received");
    }
}