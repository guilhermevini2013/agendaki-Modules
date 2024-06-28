package com.agendaki.notification.services;

import com.agendaki.notification.models.EmailContent;

public interface EmailService {

    void sendEmail(EmailContent emailContent);
}
