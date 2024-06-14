package com.agendaki.financially.models.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;

public enum TypePayment {
    CARD,
    PIX,
    BANK_SLIP;

    public Payment toPayment(String idPreUse, PaymentCreateDTO paymentCreateDTO) {
        switch (this) {
            case CARD:
                return new CardBank(idPreUse, paymentCreateDTO);
            case PIX:
                return new Pix(idPreUse, paymentCreateDTO);
            case BANK_SLIP:
                return new BankSlip(idPreUse, paymentCreateDTO);
            default:
                throw new IllegalArgumentException("Unsupported payment type");
        }
    }
}
