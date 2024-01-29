package com.app.jmspoc.service.activemq.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class YearlyReportGenerationPublisher {
    @Value("${jmspoc.queue.yearly-sales-report-generation}")
    private String destination;

    private final JmsTemplate jmsTemplate;

    public YearlyReportGenerationPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishMessage() {
        jmsTemplate.convertAndSend(destination, "");
    }
}