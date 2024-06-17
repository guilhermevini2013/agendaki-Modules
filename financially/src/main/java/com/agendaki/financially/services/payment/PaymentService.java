package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentReadDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {
    @Transactional
    void createPayment(PaymentCreateDTO paymentDTO);

    @Transactional(readOnly = true)
    PaymentReadDTO findPaymentByPreUserId();

    @Transactional
    void deletePayment();
}
