package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.models.scheduling.Professional;

public record ProfessionalReadByServiceDTO(Long id, String name) {
    public ProfessionalReadByServiceDTO(Professional professional) {
        this(professional.getId(), professional.getName());
    }
}
