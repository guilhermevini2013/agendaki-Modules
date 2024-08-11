package com.agendaki.scheduling.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record InsertServiceDTO(
        @NotBlank
        @Size(min = 3, max = 70)
        String name,
        @NotNull
        @PositiveOrZero
        BigDecimal price,
        @NotNull
        Short duration) {
}
