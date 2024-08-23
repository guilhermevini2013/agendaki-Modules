package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.PaymentNotificationDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentViewOrderDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentService {
    @Transactional
    EntityModel<PaymentReadDTO> createPayment(PaymentCreateDTO paymentDTO);

    @Transactional(readOnly = true)
    PaymentReadDTO findPaymentByPreUserId();

    @Transactional(readOnly = true)
    List<PaymentViewOrderDTO> getAllOrders();

    @Transactional
    void deletePayment();

    @Transactional
    void pushNotification(PaymentNotificationDTO paymentNotificationDTO);
}
