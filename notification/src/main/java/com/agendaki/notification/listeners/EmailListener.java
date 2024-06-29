package com.agendaki.notification.listeners;

import com.agendaki.notification.dtos.EmailPaymentToSendDTO;
import com.agendaki.notification.dtos.EmailPreUserToSendDTO;
import com.agendaki.notification.models.EmailPaymentPendingContent;
import com.agendaki.notification.models.EmailWelcomeContent;
import com.agendaki.notification.services.EmailService;
import com.agendaki.notification.services.strategy.EmailPaymentPending;
import com.agendaki.notification.services.strategy.EmailWelcome;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    private final EmailService emailService;

    public EmailListener(final EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email.preuser.pending")
    public void sendEmailPreUser(EmailPreUserToSendDTO message) {
        emailService.sendEmail(new EmailWelcome(),new EmailWelcomeContent(message.preUserSaveResponseDTO()));
    }

    @RabbitListener(queues = "email.payment.pending")
    public void sendEmailPayment(EmailPaymentToSendDTO message) {
        emailService.sendEmail(new EmailPaymentPending(), new EmailPaymentPendingContent(message));
    }
}
