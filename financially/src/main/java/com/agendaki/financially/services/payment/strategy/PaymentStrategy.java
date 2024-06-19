package com.agendaki.financially.services.payment.strategy;

import com.agendaki.financially.api.PagBankClient;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.user.PreUser;
import com.agendaki.financially.repositories.PaymentRepository;
import com.agendaki.financially.repositories.PreUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentStrategy {
    private final PaymentRepository paymentRepository;
    private final PagBankClient pagBankClient;
    private final PreUserRepository preUserRepository;
    @Value("${api.pagBank.key}")
    private String apiKey;

    public PaymentStrategy(PaymentRepository paymentRepository, PagBankClient pagBankClient, PreUserRepository preUserRepository) {
        this.paymentRepository = paymentRepository;
        this.pagBankClient = pagBankClient;
        this.preUserRepository = preUserRepository;
    }

    public Payment createPayment(PaymentCreateDTO paymentCreateDTO, IPaymentCreate iPayment) {
        Optional<PreUser> userFind = preUserRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
        Payment paymentSaved = paymentRepository.save(iPayment.createPayment(userFind.get(), paymentCreateDTO, pagBankClient, apiKey));
        return paymentSaved;
    }
}
