package com.agendaki.financially.dtos.user.response;

import com.agendaki.financially.repositories.PreUserRepository;

public record PreUserProfileResponseDTO(String name, String tradeName, String tellPhone) {

    public PreUserProfileResponseDTO(PreUserRepository.PreUserRead preUserRead) {
        this(preUserRead.getName(), preUserRead.getTradeName(), preUserRead.getTellPhone());
    }
}
