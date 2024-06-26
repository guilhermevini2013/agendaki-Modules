package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.PaymentNotificationDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {
    @Transactional
    EntityModel<PaymentReadDTO> createPayment(PaymentCreateDTO paymentDTO);

    @Transactional(readOnly = true)
    PaymentReadDTO findPaymentByPreUserId();

    @Transactional
    void deletePayment();

    @Transactional
    void pushNotification(PaymentNotificationDTO paymentNotificationDTO);
}
