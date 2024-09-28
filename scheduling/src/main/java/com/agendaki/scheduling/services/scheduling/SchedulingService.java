package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.dtos.response.SchedulingInformationColumDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SchedulingService {

    void insertScheduling(InsertSchedulingDTO insertSchedulingDTO);

    SchedulingInformationColumDTO getAllScheduling();

    List<LocalTime> getTimeFreeByUuidInstance(String uuidInstance, LocalDate date, Long idProfessional, Long idService);
}
