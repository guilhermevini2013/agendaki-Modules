package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentReadDTO;

public interface PaymentService {

    void createPayment(PaymentCreateDTO paymentDTO);

    PaymentReadDTO findPaymentByPreUserId();
}
