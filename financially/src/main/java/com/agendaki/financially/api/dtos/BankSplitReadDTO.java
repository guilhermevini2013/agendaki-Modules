package com.agendaki.financially.api.dtos;

import com.agendaki.financially.models.payment.Payment;

public class BankSplitReadDTO extends PaymentReadDTO {
    public BankSplitReadDTO(Payment payment) {
        super(payment);
    }
}
