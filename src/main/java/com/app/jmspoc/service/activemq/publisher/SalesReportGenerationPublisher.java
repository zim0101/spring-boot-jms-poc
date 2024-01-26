package com.app.jmspoc.service.jms.publisher;

import com.app.jmspoc.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SalesReportGenerationPublisher {

    @Value("${jmspoc.queue.sales-report-generation}")
    private String salesReportGenerationQueue;

    private final JmsTemplate jmsTemplate;

    public SalesReportGenerationPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishMessage(Product product) {
        jmsTemplate.convertAndSend(salesReportGenerationQueue, product);
    }
}