package com.agendaki.notification.services.strategy;

import com.agendaki.notification.models.EmailContent;
import com.agendaki.notification.models.EmailToSend;

public interface IEmailContent {
    EmailContent createEmailContent(EmailToSend emailToSend);
}
