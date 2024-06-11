package com.agendaki.financially.dtos.user;

import com.agendaki.financially.models.user.TypeSignature;
import jakarta.validation.constraints.*;

public record PreUserSaveDTO(
        @NotBlank
        @Size(min = 3, max = 70)
        String name,
        @NotBlank
        @Size(min = 3, max = 50)
        String tradeName,
        @NotBlank
        @Size(min = 6, max = 20)
        String password,
        @NotBlank
        @Email
        String email,
        @NotNull
        TypeSignature typeSignature,
        @NotBlank
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$")
        String tellPhone) {
}
