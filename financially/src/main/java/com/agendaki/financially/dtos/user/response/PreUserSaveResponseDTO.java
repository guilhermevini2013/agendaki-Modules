package com.agendaki.financially.dtos.user.response;

import com.agendaki.financially.models.user.PreUser;

public record PreUserSaveResponseDTO(String name,
                                     String tradeName,
                                     String email,
                                     String tellPhone) {
    public PreUserSaveResponseDTO(PreUser preUserSaved) {
        this(preUserSaved.getName(), preUserSaved.getTradeName(), preUserSaved.getUsername(), preUserSaved.getTellPhone());
    }
}
