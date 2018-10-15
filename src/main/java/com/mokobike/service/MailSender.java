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

    String subject;

    String templateName;

    public void sendMail(Order order, User user) throws Exception{

        String status = order.getStatus();
        switch (status){
            case "NEW"          :   subject = "Twoje zamówienie zostało zarejestrowane!";
                                    break;
            case "IN PROGRESS"  :   subject = "Twoje zamówienie zostało przyjęte do realizacji!";
                                    break;
            case "CANCELLED"    :   subject = "Twoje zamówienie zostało anulowane!";
                                    break;
        }

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom("demo@mail.com");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        String content = mailContentBuilder.build(order, user, templateName);
        helper.setText(content, true);

        javaMailSender.send(mail);

    }

    public void sendMail(User user) throws Exception{
        //TODO - user registration
    }
}
