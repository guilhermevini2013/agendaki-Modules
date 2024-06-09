package com.agendaki.financially.models.payment;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "payment")
public class Payment {
    @MongoId
    private String id;
    private String idUser;
    private BigDecimal price;
    private String cpf;
    private PaymentStatus paymentStatus;
    private TypePayment typePayment;
    private LocalDate dateOpen;
    private LocalDate dateTransaction;

    protected Payment(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction) {
        this.idUser = idUser;
        this.price = price;
        this.cpf = cpf;
        this.paymentStatus = paymentStatus;
        this.typePayment = typePayment;
        this.dateOpen = dateOpen;
        this.dateTransaction = dateTransaction;
    }
}
