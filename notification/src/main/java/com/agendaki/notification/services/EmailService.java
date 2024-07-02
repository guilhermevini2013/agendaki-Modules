package com.agendaki.notification.services;

import com.agendaki.notification.models.EmailToSend;

public interface EmailService {

    void sendEmail(EmailToSend emailToSend);
}
