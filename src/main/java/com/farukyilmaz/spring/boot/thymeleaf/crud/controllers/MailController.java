package com.farukyilmaz.spring.boot.thymeleaf.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class MailController {

    private final JavaMailSender mailSender;

    @Autowired
    public MailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendMail(String email, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error...";
        }
        mailSender.send(mimeMessage);
        return "Mail Sent!";
    }
}
