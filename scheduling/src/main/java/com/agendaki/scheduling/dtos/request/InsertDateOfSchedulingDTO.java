package com.agendaki.scheduling.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record InsertDateOfSchedulingDTO(
        Long id,
        @NotNull
        LocalTime scheduleInitial,
        @NotNull
        LocalTime scheduleFinal,
        @NotNull
        LocalTime breakInitial,
        @NotNull
        LocalTime breakFinal,
        @NotNull
        DayOfWeek dayOfWeek,
        @NotNull
        Boolean isOpen) {
}
