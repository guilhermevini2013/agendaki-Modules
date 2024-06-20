package com.agendaki.financially.api.dtos;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.user.TypeSignature;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentReadDTO {
    private BigDecimal price;
    private String cpf;
    private PaymentStatus paymentStatus;
    private TypeSignature typeSignature;
    private TypePayment typePayment;
    private LocalDate dateOpen;
    private LocalDate dateTransaction;

    public PaymentReadDTO(Payment payment) {
        this.cpf = payment.getCpf().replaceAll("\\d{3}(?=\\.)", "***");
        this.price = payment.getPrice();
        this.paymentStatus = payment.getPaymentStatus();
        this.typeSignature = payment.getTypeSignature();
        this.typePayment = payment.getTypePayment();
        this.dateOpen = payment.getDateOpen();
        this.dateTransaction = payment.getDateTransaction();
    }

    public PaymentReadDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCpf() {
        return cpf;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public TypeSignature getTypeSignature() {
        return typeSignature;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public LocalDate getDateOpen() {
        return dateOpen;
    }

    public LocalDate getDateTransaction() {
        return dateTransaction;
    }
}
