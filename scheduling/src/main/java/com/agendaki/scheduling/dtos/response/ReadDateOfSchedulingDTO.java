package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.models.scheduling.DateJobCommon;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ReadDateOfSchedulingDTO(
        Long id,
        LocalTime scheduleInitial,
        LocalTime scheduleFinal,
        LocalTime breakInitial,
        LocalTime breakFinal,
        DayOfWeek dayOfWeek,
        Boolean isOpen) {
    public ReadDateOfSchedulingDTO(DateJobCommon dateJob) {
        this(dateJob.getId(), dateJob.getStartTime(), dateJob.getEndTime(), dateJob.getBreakInitial(), dateJob.getBreakFinal(), dateJob.getDayOfWeek(), dateJob.getOpen());
    }
}
