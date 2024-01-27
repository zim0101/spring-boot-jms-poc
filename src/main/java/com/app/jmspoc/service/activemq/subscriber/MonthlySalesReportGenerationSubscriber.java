package com.app.jmspoc.service.activemq.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MonthlySalesReportGenerationSubscriber {

    private static final Logger log = LoggerFactory.getLogger(MonthlySalesReportGenerationSubscriber.class);

    @JmsListener(destination = "${jmspoc.queue.monthly-sales-report-generation}")
    public void subscribeMessage() {
        log.info("MonthlySalesReportGenerationSubscriber received");
    }
}