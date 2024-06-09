package com.agendaki.financially.models.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankSlip extends Payment {
    private String description;
    private LocalDate expiryDate;

    protected BankSlip(BigDecimal price, String cpf, PaymentStatus paymentStatus) {
        super(price, cpf, paymentStatus, TypePayment.BANK_SLIP);
        this.expiryDate = LocalDate.now().plusDays(3);
    }
}
