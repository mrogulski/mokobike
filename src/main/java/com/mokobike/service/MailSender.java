package com.mokobike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component("gMailSender")
public class MailSender {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailContentBuilder mailContentBuilder;

    public void sendMail(String from, String to, String subject, String body) throws Exception{

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        String content = mailContentBuilder.build(body);
        helper.setText(content, true);

        javaMailSender.send(mail);

    }
}
