package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadDatesOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;

import java.util.Set;

public interface DateJobService {

    ReadDatesOfSchedulingDTO insertDateOfScheduling(Set<InsertDateOfSchedulingDTO> insertDateOfSchedulingDTOS);

    ReadHolidayDTO insertHoliday(InsertHolidayDTO insertHolidayDTO);

    ReadDateOfSchedulingDTO updateDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO);

    Set<ReadDateOfSchedulingDTO> getAllDateOfScheduling();

    Set<ReadHolidayDTO> getAllHoliday();

    void deleteDateJobById(Long id);

    ReadHolidayDTO updateHolidayById(InsertHolidayDTO insertHolidayDTO,Long id);

}
