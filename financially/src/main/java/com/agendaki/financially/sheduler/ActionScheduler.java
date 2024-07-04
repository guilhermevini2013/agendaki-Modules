package com.agendaki.financially.sheduler;

import com.agendaki.financially.repositories.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ActionScheduler {
    private final RabbitTemplate rabbitTemplate;
    private final PaymentRepository paymentRepository;

    public ActionScheduler(RabbitTemplate rabbitTemplate, PaymentRepository paymentRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentRepository = paymentRepository;
    }

    @Scheduled(cron = "00 10 21 * * ?")
    public void notificationStatusPaymentToPreUser() {
        List<PaymentRepository.PaymentStatusProjection> allPaymentStatusProjection = paymentRepository.findAllPaymentStatusProjection();
        allPaymentStatusProjection.forEach(paymentStatus -> {
            System.out.println(paymentStatus);
        });
    }
}
