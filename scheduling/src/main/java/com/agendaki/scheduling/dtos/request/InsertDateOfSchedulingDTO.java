package com.agendaki.scheduling.dtos.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record InsertDateOfSchedulingDTO(LocalTime scheduleInitial,
                                        LocalTime scheduleFinal,
                                        LocalTime breakInitial,
                                        LocalTime breakFinal,
                                        DayOfWeek dayOfWeek) {
}
