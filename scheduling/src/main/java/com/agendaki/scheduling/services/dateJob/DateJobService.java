package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import org.springframework.stereotype.Service;

@Service
public interface DateJobService {

    void insertDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO);

}
