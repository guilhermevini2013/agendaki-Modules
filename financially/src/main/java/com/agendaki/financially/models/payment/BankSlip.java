package com.agendaki.financially.models.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.user.TypeSignature;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankSlip extends Payment {
    private String description;
    private LocalDate due_Date;
    private String linkPDF;
    private String barcode;

    public BankSlip(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction, String description, LocalDate expiryDate) {
        super(idUser, price, cpf, paymentStatus, typeSignature, typePayment, dateOpen, dateTransaction);
        this.description = description;
        this.due_Date = expiryDate;
    }

    public BankSlip(String idPreUser, PaymentCreateDTO paymentCreateDTO, String linkPDF, String barcode) {
        super(idPreUser, paymentCreateDTO);
        this.description = paymentCreateDTO.typeSignature().getDateBySignature().description();
        this.due_Date = LocalDate.now().plusDays(3);
        this.linkPDF = linkPDF;
        this.barcode = barcode;
    }

    public BankSlip() {
    }

    public String getDescription() {
        return description;
    }

    public String getLinkPDF() {
        return linkPDF;
    }

    public String getBarcode() {
        return barcode;
    }

    public LocalDate getDue_Date() {
        return due_Date;
    }
}
