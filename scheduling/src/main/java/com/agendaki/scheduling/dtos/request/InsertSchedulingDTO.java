package com.agendaki.scheduling.dtos.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record InsertSchedulingDTO(String idInstance, LocalDate date, LocalTime startHour, Long idService,
                                  Long idProfessional,
                                  List<ResponseFormDTO> responsesForms) {
}
