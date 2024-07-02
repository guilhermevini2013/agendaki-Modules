package com.agendaki.financially.dtos.email;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;

import java.util.Map;

public record EmailFinanciallyToSendDTO(Map<String, Object> attributesEmail,
                                        String emailTo, String typeTemplate) {

    public EmailFinanciallyToSendDTO(PreUserSaveResponseDTO preUserSaveResponseDTO) {
        this(preUserSaveResponseDTO.toMap(), preUserSaveResponseDTO.email(), "WELCOME");
    }
    public EmailFinanciallyToSendDTO(PaymentReadDTO content, String username) {
        this(content.toMapToPaymentCreated(), username, "PAYMENT_CREATED");
    }

}
