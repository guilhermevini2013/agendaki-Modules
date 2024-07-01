package com.agendaki.financially.dtos.email;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmailToPaymentDTO(String email, String signatureDescription, String cpf,
                                BigDecimal totalValue, LocalDate date) {

    public EmailToPaymentDTO(PaymentReadDTO content, String username) {
        this(username, content.getTypeSignature().getDateBySignature().description(),
                content.getCpf(), content.getTypeSignature().getDateBySignature().price(),
                content.getDateOpen());
    }

}
