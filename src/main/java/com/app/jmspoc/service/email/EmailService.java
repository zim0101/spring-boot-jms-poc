package com.app.jmspoc.service.email;

import com.app.jmspoc.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Value("${jmspoc.email.default.display-name}")
    private String defaultDisplayName;

    @Value("${jmspoc.email.default.sender-address}")
    private String defaultSenderAddress;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(EmailDto email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(email.getMailSubject());
            mailMessage.setFrom(String.valueOf(new InternetAddress(
                    email.getMailFrom() != null ? email.getMailFrom() : defaultSenderAddress,
                    email.getDisplayName() != null ? email.getDisplayName() : defaultDisplayName)));
            mailMessage.setTo(email.getMailTo());
            mailMessage.setText(email.getMailContent());
            mailSender.send(mailMessage);
        } catch (MailException | UnsupportedEncodingException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public void sendPdfEmail(EmailDto emailDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailDto.getMailTo());
            helper.setSubject(emailDto.getMailSubject());
            helper.setText(emailDto.getMailContent());
            for (String fileName: emailDto.getFileNames()) {
                File file = new File(fileName);
                helper.addAttachment("report.pdf", file);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            log.info(e.getLocalizedMessage());
        }
    }
}