package com.agendaki.notification.services.strategy;

import com.agendaki.notification.models.EmailContent;
import com.agendaki.notification.models.EmailPaymentPendingContent;
import com.agendaki.notification.models.EmailToSend;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public class EmailPaymentPending implements IEmailContent {
    @Override
    public EmailContent createEmailContent(EmailToSend emailToSend) {
        Map<String, Object> parameters = new HashMap<>();
        Context context = new Context();
        EmailPaymentPendingContent emailPaymentCreateContent = (EmailPaymentPendingContent) emailToSend;
        parameters.put("signatureDescription", emailPaymentCreateContent.getSignatureDescription());
        parameters.put("cpf", emailPaymentCreateContent.getCpf());
        parameters.put("totalValue", emailPaymentCreateContent.getTotalValue());
        parameters.put("date", emailPaymentCreateContent.getDate());
        context.setVariables(parameters);
        return new EmailContent(emailToSend.getEmail(), emailToSend.getSubject(), emailToSend.getNameResourceFile(), context);
    }
}
