package com.app.jmspoc.service.activemq.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailReportSubscriber {

    private static final Logger log = LoggerFactory.getLogger(EmailReportSubscriber.class);

    @JmsListener(destination = "${jmspoc.queue.email-report}")
    public void subscribeMessage() {
        log.info("EmailReportSubscriber received");
    }
}