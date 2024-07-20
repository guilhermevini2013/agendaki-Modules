package com.agendaki.scheduling.dtos.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public record InsertHolidayDTO(LocalTime scheduleInitial,
                               LocalTime scheduleFinal,
                               LocalTime breakInitial,
                               LocalTime breakFinal,
                               Boolean isOpen,
                               LocalDate dateOfHoliday) {
}
