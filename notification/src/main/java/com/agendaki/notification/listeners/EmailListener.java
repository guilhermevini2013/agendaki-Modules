package com.agendaki.notification.listeners;

import com.agendaki.notification.dtos.EmailFinanciallyToSendDTO;
import com.agendaki.notification.models.EmailPaymentPendingContent;
import com.agendaki.notification.models.EmailToSend;
import com.agendaki.notification.models.EmailWelcomeContent;
import com.agendaki.notification.services.EmailService;
import com.agendaki.notification.services.strategy.EmailPaymentPending;
import com.agendaki.notification.services.strategy.EmailWelcome;
import com.agendaki.notification.services.strategy.IEmailContent;
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
        IEmailContent iCreateEmailContent;
        EmailToSend emailToSend;
        switch (message.typeTemplate()) {
            case WELCOME -> {
                iCreateEmailContent = new EmailWelcome();
                emailToSend = new EmailWelcomeContent(message.preUserSaveResponseDTO().get());
            }
            case PAYMENT_CREATED -> {
                iCreateEmailContent = new EmailPaymentPending();
                emailToSend = new EmailPaymentPendingContent(message.emailPaymentToSendDTO().get());
            }
            default -> throw new IllegalArgumentException("Invalid type template");
        }
        emailService.sendEmail(iCreateEmailContent, emailToSend);
    }
}
