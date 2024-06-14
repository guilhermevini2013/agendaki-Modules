package com.agendaki.financially.dtos.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CardBankCreateDTO(
        @NotBlank(message = "Number of card is blank.")
        @Pattern(regexp = "^(?:\\d{4}[-\\s]?){3}\\d{4}$")
        String number,
        @NotBlank(message = "Due date of card is blank.")
        @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$")
        String dueDate,
        @NotBlank(message = "CVV of card is blank.")
        Short cvv,
        @NotBlank(message = "Titular name of card is blank.")
        @Size(max = 155, message = "Titular name must contain 155 characters.")
        String titularName) {
}
