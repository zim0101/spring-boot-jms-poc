package com.app.jmspoc.service.activemq.publisher;

import com.app.jmspoc.dto.EmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailReportPublisher {
    @Value("${jmspoc.queue.email-report}")
    private String destination;

    private final JmsTemplate jmsTemplate;

    public EmailReportPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishMessage(EmailDto emailDto) {
        jmsTemplate.convertAndSend(destination, emailDto);
    }
}