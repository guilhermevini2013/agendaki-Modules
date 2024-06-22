package com.agendaki.financially.services.payment;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.api.dtos.bankSlip.BankSplitReadDTO;
import com.agendaki.financially.dtos.api.dtos.pix.PixReadDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.ChargesNotificationDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.PaymentNotificationDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.exceptions.ExistingDataException;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.repositories.PaymentRepository;
import com.agendaki.financially.services.payment.strategy.BankSlipCreate;
import com.agendaki.financially.services.payment.strategy.PaymentStrategy;
import com.agendaki.financially.services.payment.strategy.PixCreate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public void pushNotification(PaymentNotificationDTO paymentNotificationDTO) {
        ChargesNotificationDTO chargesNotificationDTO = paymentNotificationDTO.charges().get(0);
        paymentRepository.updatePaymentStatusAndDateTransactionByIdPreUser(chargesNotificationDTO.reference_id(), chargesNotificationDTO.status(),
                OffsetDateTime.parse(chargesNotificationDTO.paid_at(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDate());
    }

    private String recoverIdOfAuthenticated() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
