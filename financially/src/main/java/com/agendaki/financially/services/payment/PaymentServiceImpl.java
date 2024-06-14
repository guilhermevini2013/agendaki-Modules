package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository PaymentRepository;

    public PaymentServiceImpl(PaymentRepository PaymentRepository) {
        this.PaymentRepository = PaymentRepository;
    }

    @Override
    public void createPayment(PaymentCreateDTO paymentDTO) {
        System.out.println(paymentDTO);
    }
}
