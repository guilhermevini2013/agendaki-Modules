package com.agendaki.financially.dtos.api.dtos;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.preuser.TypeSignature;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentReadDTO {
    private String paymentId;
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
        this.paymentId = payment.getId();
    }

    public PaymentReadDTO() {
    }

    public String getPaymentId() {
        return paymentId;
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

    public Map<String, Object> toMapToPaymentCreated() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cpf", cpf);
        result.put("price", price);
        result.put("paymentStatus", paymentStatus.getStatusInPtBr());
        result.put("signatureDescription", typeSignature.getInformation().description());
        result.put("typePayment", typePayment);
        result.put("dateOpen", new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(dateOpen)));
        return result;
    }

}
