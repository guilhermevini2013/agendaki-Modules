package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;

public interface PaymentService {

    void createPayment(PaymentCreateDTO paymentDTO);
}
