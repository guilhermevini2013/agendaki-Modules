package com.agendaki.financially.dtos.payment;

import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.user.TypeSignature;

import java.util.Optional;

public record PaymentCreateDTO(String cpf,
                               TypePayment typePayment,
                               TypeSignature typeSignature,
                               Optional<CardBankCreateDTO> payment) {

}
