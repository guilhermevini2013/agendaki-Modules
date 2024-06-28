package com.agendaki.notification.dtos;

import com.agendaki.notification.models.TypeTemplate;

public record EmailToSendDTO(PreUserSaveResponseDTO preUserSaveResponseDTO, TypeTemplate typeTemplate) {
}
