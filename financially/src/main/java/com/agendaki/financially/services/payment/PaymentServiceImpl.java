package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentReadDTO;
import com.agendaki.financially.exceptions.ExistingDataException;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.repositories.PaymentRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PasswordEncoder passwordEncoder) {
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createPayment(PaymentCreateDTO paymentDTO) {
        try {
            paymentRepository.save(paymentDTO.typePayment().toPayment(recoverIdOfAuthenticated(), paymentDTO, passwordEncoder));
        } catch (DuplicateKeyException ex) {
            throw new ExistingDataException("Request already active on your account");
        }
    }

    @Override
    public PaymentReadDTO findPaymentByPreUserId() {
        Payment payment = paymentRepository.findByIdPreUser(recoverIdOfAuthenticated()).orElse(null);
        if (payment == null) {
            return null;
        }
        return new PaymentReadDTO(payment);
    }

    private String recoverIdOfAuthenticated() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
