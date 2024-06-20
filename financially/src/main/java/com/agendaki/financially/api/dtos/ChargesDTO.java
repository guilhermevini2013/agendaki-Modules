package com.agendaki.financially.api.dtos;

import com.agendaki.financially.api.dtos.bankSlip.PaymentMethodDTO;
import com.agendaki.financially.api.dtos.pix.AmountDTO;

public record ChargesDTO(String reference_id, String description, AmountDTO amount, PaymentMethodDTO payment_method) {
}
