package com.agendaki.notification.services;

import com.agendaki.notification.models.EmailToSend;
import com.agendaki.notification.services.strategy.IEmailContent;

public interface EmailService {

    void sendEmail(IEmailContent iCreateEmailContent, EmailToSend emailToSend);
}
