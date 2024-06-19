package com.agendaki.financially.services.payment;

import com.agendaki.financially.api.dtos.BankSplitReadDTO;
import com.agendaki.financially.api.dtos.CardBankReadDTO;
import com.agendaki.financially.api.dtos.PaymentReadDTO;
import com.agendaki.financially.api.dtos.pix.PixReadDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.exceptions.ExistingDataException;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.repositories.PaymentRepository;
import com.agendaki.financially.services.payment.strategy.BankSlipCreate;
import com.agendaki.financially.services.payment.strategy.CardBankCreate;
import com.agendaki.financially.services.payment.strategy.PaymentStrategy;
import com.agendaki.financially.services.payment.strategy.PixCreate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategy paymentStrategy;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStrategy paymentStrategy) {
        this.paymentRepository = paymentRepository;
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public PaymentReadDTO createPayment(PaymentCreateDTO paymentDTO) {
        try {
            switch (paymentDTO.typePayment()) {
                case CARD -> {
                    return new CardBankReadDTO(paymentStrategy.createPayment(paymentDTO, new CardBankCreate()));
                }
                case PIX -> {
                    return new PixReadDTO(paymentStrategy.createPayment(paymentDTO, new PixCreate()));
                }
                case BANK_SLIP -> {
                    return new BankSplitReadDTO(paymentStrategy.createPayment(paymentDTO, new BankSlipCreate()));
                }
                default -> throw new IllegalArgumentException("Type payment not supported");
            }
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
        switch (payment.getTypePayment()) {
            case CARD -> {
                return new CardBankReadDTO(payment);
            }
            case PIX -> {
                return new PixReadDTO(payment);
            }
            case BANK_SLIP -> {
                return new BankSplitReadDTO(payment);
            }
            default -> throw new IllegalArgumentException("Type payment not supported");
        }
    }

    @Override
    public void deletePayment() {
        String idAuthenticated = recoverIdOfAuthenticated();
        if (!paymentRepository.existsByIdPreUser(idAuthenticated)) {
            throw new ExistingDataException("Not a payment on your account");
        }
        paymentRepository.deleteByIdPreUser(idAuthenticated);
    }

    private String recoverIdOfAuthenticated() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
