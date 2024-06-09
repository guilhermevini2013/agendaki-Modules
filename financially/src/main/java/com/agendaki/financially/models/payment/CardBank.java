package com.agendaki.financially.models.payment;

import java.math.BigDecimal;

public class CardBank extends Payment {
    private String number;
    private String due_Date;
    private Short cvv;
    private String titularName;

    protected CardBank(BigDecimal price, String cpf, PaymentStatus paymentStatus) {
        super(price, cpf, paymentStatus, TypePayment.CARD);
    }
}
