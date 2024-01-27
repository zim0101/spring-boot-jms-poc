package com.app.jmspoc.service.activemq.publisher;

import com.app.jmspoc.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class DailyReportGenerationPublisher {
    @Value("${jmspoc.queue.daily-sales-report-generation}")
    private String destination;

    private final JmsTemplate jmsTemplate;

    public DailyReportGenerationPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishMessage() {
        jmsTemplate.convertAndSend(destination, "");
    }
}