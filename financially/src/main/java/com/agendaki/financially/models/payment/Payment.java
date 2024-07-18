package com.agendaki.financially.models.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.preuser.TypeSignature;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "payment")
public abstract class Payment {
    @MongoId
    private String id;
    @Indexed(unique = true)
    private String idPreUser;
    private BigDecimal price;
    private String cpf;
    private PaymentStatus paymentStatus;
    private TypeSignature typeSignature;
    private TypePayment typePayment;
    private LocalDate dateOpen;
    private LocalDate dateTransaction;

    protected Payment(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction) {
        this.idPreUser = idUser;
        this.price = price;
        this.cpf = cpf;
        this.paymentStatus = paymentStatus;
        this.typeSignature = typeSignature;
        this.typePayment = typePayment;
        this.dateOpen = dateOpen;
        this.dateTransaction = dateTransaction;
    }

    protected Payment(String idPreUser, PaymentCreateDTO paymentCreateDTO) {
        this.idPreUser = idPreUser;
        this.typePayment = paymentCreateDTO.typePayment();
        this.price = paymentCreateDTO.typeSignature().getInformation().price();
        this.typeSignature = paymentCreateDTO.typeSignature();
        this.dateOpen = LocalDate.now();
        this.paymentStatus = PaymentStatus.WAITING;
        this.dateTransaction = null;
        this.cpf = paymentCreateDTO.cpf();
    }

    public Payment() {
    }

    public String getId() {
        return id;
    }

    public String getIdPreUser() {
        return idPreUser;
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

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public TypeSignature getTypeSignature() {
        return typeSignature;
    }

    public LocalDate getDateOpen() {
        return dateOpen;
    }

    public LocalDate getDateTransaction() {
        return dateTransaction;
    }
}
