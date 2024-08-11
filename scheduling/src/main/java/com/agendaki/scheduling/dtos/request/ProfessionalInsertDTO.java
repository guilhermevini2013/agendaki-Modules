package com.agendaki.scheduling.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record ProfessionalInsertDTO(
        @Size(min = 3, max = 60)
        @NotBlank
        String name,
        @NotEmpty
        Set<Long> servicesToJobIDs
) {
}
