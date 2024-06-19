package com.agendaki.financially.services.payment.strategy;

import com.agendaki.financially.api.PagBankClient;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.user.PreUser;

public class BankSlipCreate implements IPaymentCreate {
    @Override
    public Payment createPayment(PreUser preUser, PaymentCreateDTO paymentDTO, PagBankClient pagBankClient, String apiKey) {
        return null;
    }
}
