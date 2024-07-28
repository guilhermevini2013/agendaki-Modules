package com.agendaki.scheduling.dtos.response;
import com.agendaki.scheduling.models.scheduling.DateJobHoliday;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReadHolidayDTO(Long id,
                             LocalTime scheduleInitial,
                             LocalTime scheduleFinal,
                             LocalTime breakInitial,
                             LocalTime breakFinal,
                             Boolean isOpen,
                             LocalDate dateOfHoliday) {
    public ReadHolidayDTO(DateJobHoliday dateJobHoliday) {
        this(dateJobHoliday.getId(),dateJobHoliday.getStartTime(),dateJobHoliday.getEndTime(),dateJobHoliday.getBreakInitial(),dateJobHoliday.getBreakFinal(),
                dateJobHoliday.getOpen(),dateJobHoliday.getDate());
    }
}
