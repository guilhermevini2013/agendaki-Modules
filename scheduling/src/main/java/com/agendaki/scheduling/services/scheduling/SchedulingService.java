package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import org.springframework.stereotype.Service;

@Service
public interface SchedulingService {
    void insertDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO);
}
