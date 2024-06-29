package com.agendaki.financially.dtos.email;

import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;

public record EmailToPreUserDTO(PreUserSaveResponseDTO preUserSaveResponseDTO, String typeTemplate) {
}
