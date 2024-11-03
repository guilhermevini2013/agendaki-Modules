package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.models.scheduling.Scheduling;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record AllSchedulingReadDTO(List<SchedulingResponse> responseScheduling, Long idScheduling) {
    public AllSchedulingReadDTO(Scheduling scheduling) {
        this(createResponseSchedulingList(scheduling), scheduling.getId());
    }

    private static List<SchedulingResponse> createResponseSchedulingList(Scheduling scheduling) {
        List<SchedulingResponse> responseScheduling = scheduling.getResponseForms().stream()
                .map(responseForm -> new SchedulingResponse(responseForm.getInput().getLabel(), responseForm.getResponse()))
                .collect(Collectors.toList());
        responseScheduling.add(new SchedulingResponse("Data", new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(scheduling.getDate()))));
        responseScheduling.add(new SchedulingResponse("Horário", scheduling.getStartHour().toString()));
        responseScheduling.add(new SchedulingResponse("Profissional", scheduling.getProfessional().getName()));
        responseScheduling.add(new SchedulingResponse("Serviço", scheduling.getService().getName()));
        return responseScheduling;
    }

    public record SchedulingResponse(String nameColum, String value) {
    }
}
