package com.agendaki.scheduling.dtos.request;

import java.time.LocalTime;
import java.util.Optional;

public record InsertDateOfSchedulingDTO(LocalTime scheduleInitial,
                                        LocalTime scheduleFinal,
                                        LocalTime breakInitial,
                                        LocalTime breakFinal,
                                        Optional<DateJobCommon> dateJobCommon) {
}
