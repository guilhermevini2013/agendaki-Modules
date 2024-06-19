package com.agendaki.financially.api.dtos;

import com.agendaki.financially.models.payment.Payment;

public class CardBankReadDTO extends PaymentReadDTO {
    public CardBankReadDTO(Payment payment) {
        super(payment);
    }
}
