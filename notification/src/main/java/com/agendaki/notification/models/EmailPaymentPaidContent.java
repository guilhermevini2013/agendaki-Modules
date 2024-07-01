package com.agendaki.notification.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmailPaymentPaidContent extends EmailToSend {
    private final String username;
    private final String status;
    private final String signatureDescription;
    private final LocalDate date;
    private final BigDecimal totalValue;

    public EmailPaymentPaidContent(String email, String subject, String nameResourceFile, String username, String status, String signatureDescription, LocalDate date, BigDecimal totalValue) {
        super(email, subject, nameResourceFile);
        this.username = username;
        this.status = status;
        this.signatureDescription = signatureDescription;
        this.date = date;
        this.totalValue = totalValue;
    }
}
