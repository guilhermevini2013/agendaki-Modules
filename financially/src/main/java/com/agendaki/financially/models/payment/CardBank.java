package com.agendaki.financially.models.payment;

import com.agendaki.financially.models.user.TypeSignature;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardBank extends Payment {
    private String number;
    private String dueDate;
    private Short cvv;
    private String titularName;

    public CardBank(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction, String number, String dueDate, Short cvv, String titularName) {
        super(idUser, price, cpf, paymentStatus, typeSignature, typePayment, dateOpen, dateTransaction);
        this.number = number;
        this.dueDate = dueDate;
        this.cvv = cvv;
        this.titularName = titularName;
    }
}
