package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.models.scheduling.DateJobCommon;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ReadDateOfSchedulingDTO(
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

    public ReadDateOfSchedulingDTO(DateJobCommon dateJobFind) {
        this(dateJobFind.getStartTime(), dateJobFind.getEndTime(), dateJobFind.getBreakInitial(), dateJobFind.getBreakFinal(), dateJobFind.getDayOfWeek());
    }
}
