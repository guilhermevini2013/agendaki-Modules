package com.agendaki.notification.listeners;

import com.agendaki.notification.dtos.EmailGenerationDTO;
import com.agendaki.notification.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    private final EmailService emailService;

    public EmailListener(final EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email.pending")
    public void sendEmail(EmailGenerationDTO message) {
        emailService.sendEmail(message.typeReceiver().getEmailContent(message.email()));
    }
}
