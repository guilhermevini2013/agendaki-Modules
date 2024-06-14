package com.agendaki.financially.models.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.user.TypeSignature;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pix extends Payment {
    @Value("${keyPIX}")
    private static String PIX_KEY_RECEIVER;

    public Pix(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction) {
        super(idUser, price, cpf, paymentStatus, typeSignature, typePayment, dateOpen, dateTransaction);
    }

    public Pix(String idPreUser, PaymentCreateDTO paymentCreateDTO) {
        super(idPreUser, paymentCreateDTO);
    }
}
