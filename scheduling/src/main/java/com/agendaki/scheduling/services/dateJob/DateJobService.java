package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDatesOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface DateJobService {

    EntityModel<ReadDatesOfSchedulingDTO> insertDateOfScheduling(Set<InsertDateOfSchedulingDTO> insertDateOfSchedulingDTO);

    EntityModel<ReadHolidayDTO> insertHoliday(InsertHolidayDTO insertHolidayDTO);

}
