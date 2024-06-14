package com.agendaki.financially.dtos.payment;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.user.TypeSignature;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentReadDTO(BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature,
                             TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction) {
    public PaymentReadDTO(Payment payment) {
        this(payment.getPrice(), payment.getCpf().replaceAll("\\d{3}(?=\\.)", "***"),
                payment.getPaymentStatus(), payment.getTypeSignature(), payment.getTypePayment(), payment.getDateOpen(), payment.getDateTransaction());
    }
}
