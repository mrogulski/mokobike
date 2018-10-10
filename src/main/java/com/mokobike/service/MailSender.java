package com.mokobike.service;

import com.mokobike.domain.Order;
import com.mokobike.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component("gMailSender")
public class MailSender {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailContentBuilder mailContentBuilder;

    public void sendMail(Order order, User user) throws Exception{

        String subject = "Twoje zamówienie zostało zarejestrowane!";

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom("demo@mail.com");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        String content = mailContentBuilder.build(order, user);
        helper.setText(content, true);

        javaMailSender.send(mail);

    }
}
