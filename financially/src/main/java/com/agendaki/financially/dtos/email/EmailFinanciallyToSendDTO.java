package com.agendaki.financially.dtos.email;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;

import java.util.Optional;

public record EmailFinanciallyToSendDTO(Optional<PreUserSaveResponseDTO> preUserSaveResponseDTO,
                                        Optional<EmailToPaymentDTO> emailPaymentToSendDTO, String typeTemplate) {

    public EmailFinanciallyToSendDTO(PreUserSaveResponseDTO preUserSaveResponseDTO) {
        this(Optional.of(preUserSaveResponseDTO),Optional.empty(),"WELCOME");
    }
    public EmailFinanciallyToSendDTO(PaymentReadDTO content, String username) {
        this(Optional.empty(), Optional.of(new EmailToPaymentDTO(content, username)), "PAYMENT_CREATED");
    }


}
