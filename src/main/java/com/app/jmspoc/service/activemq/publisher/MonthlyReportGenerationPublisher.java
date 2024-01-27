package com.app.jmspoc.service.activemq.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MonthlyReportGenerationPublisher {
    @Value("${jmspoc.queue.monthly-sales-report-generation}")
    private String destination;

    private final JmsTemplate jmsTemplate;

    public MonthlyReportGenerationPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishMessage() {
        jmsTemplate.convertAndSend(destination, "");
    }
}