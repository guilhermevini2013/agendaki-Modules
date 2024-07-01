package com.agendaki.notification.dtos;

import com.agendaki.notification.models.TypeTemplate;

import java.util.Optional;

public record EmailFinanciallyToSendDTO(Optional<PreUserSaveResponseDTO> preUserSaveResponseDTO,Optional<EmailPaymentToSendDTO> emailPaymentToSendDTO, TypeTemplate typeTemplate) {
}
