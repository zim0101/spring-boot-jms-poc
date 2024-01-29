package com.app.jmspoc.service.activemq.subscriber;

import com.app.jmspoc.dto.EmailDto;
import com.app.jmspoc.service.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailReportSubscriber {

    private static final Logger log = LoggerFactory.getLogger(EmailReportSubscriber.class);
    private final EmailService emailService;

    public EmailReportSubscriber(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = "${jmspoc.queue.email-report}")
    public void subscribeMessage(EmailDto emailDto) {
        log.info("EmailReportSubscriber received");
        emailService.sendPdfEmail(emailDto);
    }
}