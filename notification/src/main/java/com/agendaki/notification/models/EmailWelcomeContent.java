package com.agendaki.notification.models;

import com.agendaki.notification.dtos.financially.PreUserSaveResponseDTO;

public class EmailWelcomeContent extends EmailToSend {
    private final String username;
    private final String tradeName;

    public EmailWelcomeContent(PreUserSaveResponseDTO preUserSaveResponseDTO) {
        super(preUserSaveResponseDTO.email(), "Bem-vindo a plataforma AgendaKi", "welcomePreUser");
        this.username = preUserSaveResponseDTO.name();
        this.tradeName = preUserSaveResponseDTO.tradeName();
    }

    public String getUsername() {
        return username;
    }

    public String getTradeName() {
        return tradeName;
    }
}
