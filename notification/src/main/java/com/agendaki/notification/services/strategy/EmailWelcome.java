package com.agendaki.notification.services.strategy;

import com.agendaki.notification.models.EmailContent;
import com.agendaki.notification.models.EmailToSend;
import com.agendaki.notification.models.EmailWelcomeContent;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public class EmailWelcome implements IEmailContent {
    @Override
    public EmailContent createEmailContent(EmailToSend emailToSend) {
        Map<String, Object> parameters = new HashMap<>();
        Context context = new Context();
        EmailWelcomeContent emailPaymentCreateContent = (EmailWelcomeContent) emailToSend;
        parameters.put("username", emailPaymentCreateContent.getUsername());
        parameters.put("tradeName", emailPaymentCreateContent.getTradeName());
        context.setVariables(parameters);
        return new EmailContent(emailToSend.getEmail(), emailToSend.getSubject(), emailToSend.getNameResourceFile(), context);
    }
}
