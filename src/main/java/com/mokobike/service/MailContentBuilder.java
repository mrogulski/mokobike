package com.mokobike.service;

import com.mokobike.domain.Order;
import com.mokobike.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(Order order, User user, String templateName){
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("user", user);
        return templateEngine.process("mail/"+ templateName, context);
    }
}
