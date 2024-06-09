package com.agendaki.financially.models.payment;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pix extends Payment {
    @Value("${keyPIX}")
    private static String PIX_KEY_RECEIVER;

    protected Pix(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, LocalDate dateOpen, LocalDate dateTransaction) {
        super(idUser, price, cpf, paymentStatus, TypePayment.PIX, dateOpen, dateTransaction);
    }
}
