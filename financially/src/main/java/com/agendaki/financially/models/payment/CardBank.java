package com.agendaki.financially.models.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardBank extends Payment {
    private String number;
    private String dueDate;
    private Short cvv;
    private String titularName;

    protected CardBank(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, LocalDate dateOpen, LocalDate dateTransaction) {
        super(idUser, price, cpf, paymentStatus, TypePayment.CARD, dateOpen, dateTransaction);
    }
}
