package com.agendaki.scheduling.dtos.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record InsertHolidayDTO(
        @NotNull
        LocalTime scheduleInitial,
        @NotNull
        LocalTime scheduleFinal,
        @NotNull
        LocalTime breakInitial,
        @NotNull
        LocalTime breakFinal,
        @NotNull
        Boolean isOpen,
        @FutureOrPresent(message = "Data should be to present or future")
        @NotNull
        LocalDate dateOfHoliday) {
}
