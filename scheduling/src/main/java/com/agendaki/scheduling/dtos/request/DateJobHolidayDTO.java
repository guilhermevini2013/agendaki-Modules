package com.agendaki.scheduling.dtos.request;

import java.time.LocalDate;

public record DateJobHolidayDTO(Boolean isOpen, LocalDate dateOfHoliday) {
}
