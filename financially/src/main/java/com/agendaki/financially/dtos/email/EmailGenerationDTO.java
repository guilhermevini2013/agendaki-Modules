package com.agendaki.financially.dtos.email;

import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;

public record EmailGenerationDTO(PreUserSaveResponseDTO preUserSaveResponseDTO, String typeTemplate) {
}
