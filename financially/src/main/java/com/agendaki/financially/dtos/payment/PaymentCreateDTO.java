package com.agendaki.financially.dtos.payment;

import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.user.TypeSignature;

import java.math.BigDecimal;

public record PaymentCreateDTO(BigDecimal price,
                               String cpf,
                               PaymentStatus paymentStatus,
                               TypePayment typePayment,
                               TypeSignature typeSignature,
                               IPaymentDTO iPayment) {

}
