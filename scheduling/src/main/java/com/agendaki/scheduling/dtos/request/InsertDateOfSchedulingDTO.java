package com.agendaki.scheduling.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record InsertDateOfSchedulingDTO(
        @NotNull
        LocalTime scheduleInitial,
        @NotNull
        LocalTime scheduleFinal,
        @NotNull
        LocalTime breakInitial,
        @NotNull
        LocalTime breakFinal,
        @NotNull
        DayOfWeek dayOfWeek) { 
}
