package com.agendaki.financially.api.dtos.pix;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.user.PreUser;

import java.util.List;

public record PaymentPixCreateDTO(String reference_id, CustomerDTO customer, List<QRcodeDTO> qr_codes) {
    public PaymentPixCreateDTO(PaymentCreateDTO paymentDTO, PreUser preUser) {
        this(preUser.getId(), new CustomerDTO(preUser.getName(), preUser.getUsername(), paymentDTO.cpf().replace(".", "").replace("-", "")), List.of(new QRcodeDTO(new AmountDTO(paymentDTO.typeSignature().getDateBySignature().price().toString()))));
    }
}
