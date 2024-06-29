package com.agendaki.notification.dtos;

import com.agendaki.notification.models.TypeTemplate;

public record EmailPreUserToSendDTO(PreUserSaveResponseDTO preUserSaveResponseDTO, TypeTemplate typeTemplate) {
}
