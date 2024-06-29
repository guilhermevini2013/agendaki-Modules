package com.agendaki.notification.services;

import com.agendaki.notification.models.EmailContent;
import com.agendaki.notification.models.EmailToSend;
import com.agendaki.notification.services.strategy.IEmailContent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(IEmailContent iCreateEmailContent, EmailToSend emailToSend) {
        EmailContent emailContent = iCreateEmailContent.createEmailContent(emailToSend);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(emailContent.email());
            helper.setSubject(emailContent.subject());
            String htmlContent = templateEngine.process(emailContent.nameResourceFile(), emailContent.context());
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
