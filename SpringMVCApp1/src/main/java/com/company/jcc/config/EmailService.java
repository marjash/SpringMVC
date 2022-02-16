package com.company.jcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Properties;

@Service("EmailService")
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public EmailService() {
    }

    public void sendSimpleMessage(String subject, String text, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public JavaMailSender getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
}
