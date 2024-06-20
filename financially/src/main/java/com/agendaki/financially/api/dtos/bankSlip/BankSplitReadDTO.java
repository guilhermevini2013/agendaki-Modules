package com.agendaki.financially.api.dtos.bankSlip;

import com.agendaki.financially.api.dtos.PaymentReadDTO;
import com.agendaki.financially.models.payment.BankSlip;
import com.agendaki.financially.models.payment.Payment;

import java.time.LocalDate;

public class BankSplitReadDTO extends PaymentReadDTO {
    private final String description;
    private final LocalDate due_Date;
    private final String linkPDF;
    private final String barcode;

    public BankSplitReadDTO(Payment payment) {
        super(payment);
        BankSlip bankSlip = (BankSlip) payment;
        this.description = bankSlip.getDescription();
        this.due_Date = bankSlip.getDue_Date();
        this.linkPDF = bankSlip.getLinkPDF();
        this.barcode = bankSlip.getBarcode();
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDue_Date() {
        return due_Date;
    }

    public String getLinkPDF() {
        return linkPDF;
    }

    public String getBarcode() {
        return barcode;
    }
}
