package com.agendaki.scheduling.dtos.response;

import java.util.List;

public record SchedulingInformationColumDTO(List<String> columns, List<AllSchedulingReadDTO> allScheduling) {
}
