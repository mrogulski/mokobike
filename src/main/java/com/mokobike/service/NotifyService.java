package com.mokobike.service;

import com.mokobike.domain.Order;
import com.mokobike.domain.User;
import com.mokobike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {

    @Autowired
    @Qualifier("gMailSender")
    MailSender mailSender;

    @Autowired
    UserRepository userRepository;

    User user;


    public void notify(Order order) throws Exception{//TODO change to try catch in case of failed when sending mail
//        user = userRepository.findByID(order.getUser().getId());
//        mailSender.sendMail(order, user);
    }
}
