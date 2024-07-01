package com.agendaki.notification.models;

import com.agendaki.notification.dtos.financially.EmailPaymentToSendDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmailPaymentPendingContent extends EmailToSend {
    private final String signatureDescription;
    private final String cpf;
    private final BigDecimal totalValue;
    private final LocalDate date;

    public EmailPaymentPendingContent(EmailPaymentToSendDTO emailPaymentToSendDTO) {
        super(emailPaymentToSendDTO.email(), "Pedido de pagamento pendente!", "paymentCreate");
        this.signatureDescription = emailPaymentToSendDTO.signatureDescription();
        this.cpf = emailPaymentToSendDTO.cpf();
        this.totalValue = emailPaymentToSendDTO.totalValue();
        this.date = emailPaymentToSendDTO.date();
    }

    public String getSignatureDescription() {
        return signatureDescription;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public LocalDate getDate() {
        return date;
    }
}
