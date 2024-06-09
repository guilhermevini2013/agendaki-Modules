package com.agendaki.financially.models.payment;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Document(collection = "payment")
public class Payment {
    @MongoId
    private String id;
    private BigDecimal price;
    private String cpf;
    private PaymentStatus paymentStatus;
    private TypePayment typePayment;

    protected Payment(BigDecimal price, String cpf, PaymentStatus paymentStatus, TypePayment typePayment) {
        this.price = price;
        this.cpf = cpf;
        this.paymentStatus = paymentStatus;
        this.typePayment = typePayment;
    }
}
