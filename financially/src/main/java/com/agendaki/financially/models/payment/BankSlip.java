package com.agendaki.financially.models.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankSlip extends Payment {
    private String description;
    private LocalDate expiryDate;


    protected BankSlip(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, LocalDate dateOpen, LocalDate dateTransaction) {
        super(idUser, price, cpf, paymentStatus, TypePayment.BANK_SLIP, dateOpen, dateTransaction);
    }
}
