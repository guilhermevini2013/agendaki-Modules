package com.agendaki.notification.dtos;

import com.agendaki.notification.models.TypeTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmailPaymentToSendDTO(String email, String signatureDescription, String cpf,
                                    BigDecimal totalValue, LocalDate date, TypeTemplate typeTemplate) {
}
