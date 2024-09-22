package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.models.scheduling.Scheduling;

import java.util.List;

public record AllSchedulingReadDTO(List<SchedulingResponse> responseScheduling, Long idScheduling) {
    public AllSchedulingReadDTO(Scheduling scheduling) {
        this(scheduling.getResponseForms().stream().map(
                        responseForm -> new SchedulingResponse(responseForm.getInput().getLabel(), responseForm.getResponse())).toList()
                , scheduling.getId());
    }

    record SchedulingResponse(String nameColum, String value) {
    }
}
