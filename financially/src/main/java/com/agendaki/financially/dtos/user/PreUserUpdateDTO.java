package com.agendaki.financially.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PreUserUpdateDTO(
        @NotBlank(message = "Name is blank.")
        @Size(min = 3, max = 70, message = "Name must contain 3 to 70 characters.")
        String name,
        @NotBlank(message = "Trade name is blank.")
        @Size(min = 3, max = 50, message = "Trade name must contain 3 to 50 characters.")
        String tradeName,
        @NotBlank(message = "TellPhone is blank")
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Pattern incorrect, use (00) 00000-0000.")
        String tellPhone) {
}
