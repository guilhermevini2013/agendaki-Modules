package com.agendaki.financially.models.payment;

import java.math.BigDecimal;

public class Pix extends Payment {
    private static final String PIX_KEY_RECEIVER = "";

    protected Pix(BigDecimal price, String cpf, PaymentStatus paymentStatus) {
        super(price, cpf, paymentStatus, TypePayment.PIX);
    }
}
