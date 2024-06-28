package com.agendaki.notification.models;

import com.agendaki.notification.dtos.PreUserSaveResponseDTO;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public enum TypeTemplate {
    WELCOME;

    public EmailContent getEmailContent(PreUserSaveResponseDTO preUserSaveDTO) {
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        switch (this) {
            case WELCOME:
                variables.put("username", preUserSaveDTO.name());
                variables.put("tradeName", preUserSaveDTO.tradeName());
                context.setVariables(variables);
                return new EmailContent(preUserSaveDTO.email(), "Bem-vindo a plataforma AgendaKi", "welcomePreUser", context);
            default:
                throw new IllegalArgumentException("Invalid Type template");
        }
    }
}
