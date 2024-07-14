package com.agendaki.notification.listeners;

import com.agendaki.notification.dtos.financially.EmailFinanciallyToSendDTO;
import com.agendaki.notification.models.EmailToSend;
import com.agendaki.notification.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    private final EmailService emailService;

    public EmailListener(final EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email.financially")
    public void sendEmailFinancially(EmailFinanciallyToSendDTO message) {
        emailService.sendEmail(new EmailToSend(message.emailTo(), message.typeTemplate().getSubject(), message.typeTemplate().getResourceFileName(), message.attributes()));
    }
}
