package com.agendaki.financially.dtos.payment;

import com.agendaki.financially.models.payment.Payment;

import java.math.BigDecimal;

public record PaymentViewOrderDTO(String titlePlan, String descriptionPlan, BigDecimal valuePlan,
                                  String paymentStatus) {

    public PaymentViewOrderDTO(Payment payment) {
        this(payment.getTypeSignature().getValue(), payment.getTypeSignature().getInformation().description(), payment.getPrice(), payment.getPaymentStatus().getStatusInPtBr());
    }

}
