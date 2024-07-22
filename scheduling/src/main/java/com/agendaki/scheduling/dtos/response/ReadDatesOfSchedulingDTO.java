package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;

import java.util.Set;

public record ReadDatesOfSchedulingDTO(Set<InsertDateOfSchedulingDTO> datesOfSchedulingInsert) {
}
