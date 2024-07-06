package com.agendaki.financially.services.payment.strategy;

import com.agendaki.financially.api.PagBankClient;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.preuser.PreUser;

public interface IPaymentCreate {
    Payment createPayment(PreUser preUser, PaymentCreateDTO paymentDTO, PagBankClient pagBankClient, String apiKey);
}
