package com.app.jmspoc.service.activemq.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class YearlySalesReportGenerationSubscriber {

    private static final Logger log = LoggerFactory.getLogger(YearlySalesReportGenerationSubscriber.class);

    @JmsListener(destination = "${jmspoc.queue.yearly-sales-report-generation}")
    public void subscribeMessage() {
        log.info("YearlySalesReportGenerationSubscriber received");
    }
}