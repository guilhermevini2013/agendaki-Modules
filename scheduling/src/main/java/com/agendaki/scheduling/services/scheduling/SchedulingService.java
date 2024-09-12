package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.dtos.response.SchedulingInformationColumDTO;

public interface SchedulingService {

    void insertScheduling(InsertSchedulingDTO insertSchedulingDTO);

    SchedulingInformationColumDTO getAllScheduling();
}
