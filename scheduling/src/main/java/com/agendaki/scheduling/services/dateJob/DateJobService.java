package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface DateJobService {

    void insertDateOfScheduling(Set<InsertDateOfSchedulingDTO> insertDateOfSchedulingDTO);

    void insertHoliday(InsertHolidayDTO insertHolidayDTO);

}
