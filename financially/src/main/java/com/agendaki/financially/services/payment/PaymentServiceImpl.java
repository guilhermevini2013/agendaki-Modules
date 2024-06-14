package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.repositories.PaymentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void createPayment(PaymentCreateDTO paymentDTO) {
        paymentRepository.save(paymentDTO.typePayment().toPayment(recoverIdOfAuthenticated(), paymentDTO));
    }

    private String recoverIdOfAuthenticated() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
