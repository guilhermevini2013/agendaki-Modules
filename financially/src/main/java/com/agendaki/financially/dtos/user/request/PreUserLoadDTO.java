package com.agendaki.financially.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PreUserLoadDTO(@Email
                             @NotBlank(message = "Email is blank.")
                             String email,
                             @NotBlank(message = "Password is blank.")
                             String password) {
}
