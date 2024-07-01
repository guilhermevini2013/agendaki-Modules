package com.agendaki.notification.services.strategy;

import com.agendaki.notification.models.EmailContent;
import com.agendaki.notification.models.EmailToSend;

public class EmailPaymentPaid implements IEmailContent{
    @Override
    public EmailContent createEmailContent(EmailToSend emailToSend) {
        return null;
    }
}
