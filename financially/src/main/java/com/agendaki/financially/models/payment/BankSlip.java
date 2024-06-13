package com.agendaki.financially.models.payment;

import com.agendaki.financially.models.user.TypeSignature;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankSlip extends Payment {
    private String description;
    private LocalDate expiryDate;

    public BankSlip(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction, String description, LocalDate expiryDate) {
        super(idUser, price, cpf, paymentStatus, typeSignature, typePayment, dateOpen, dateTransaction);
        this.description = description;
        this.expiryDate = expiryDate;
    }
}
