package com.agendaki.scheduling.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoadDTO(
        @NotBlank(message = "Email is blank")
        @Email
        String email,
        @NotBlank(message = "Password is blank")
        String password) {
}
