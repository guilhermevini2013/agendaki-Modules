package com.agendaki.notification.dtos;

import com.agendaki.notification.models.TypeTemplate;

public record EmailGenerationDTO(PreUserSaveResponseDTO preUserSaveResponseDTO, TypeTemplate typeTemplate) {
}
