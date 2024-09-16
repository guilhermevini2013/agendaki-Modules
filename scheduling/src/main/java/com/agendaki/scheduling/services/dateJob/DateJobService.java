package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;

import java.util.List;
import java.util.Set;

public interface DateJobService {

    ReadDateOfSchedulingDTO insertDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO);

    ReadHolidayDTO insertHoliday(InsertHolidayDTO insertHolidayDTO);

    List<ReadDateOfSchedulingDTO> getAllDateOfScheduling();

    Set<ReadHolidayDTO> getAllHoliday();

    void deleteDateJobById(Long id);

    ReadHolidayDTO updateHolidayById(InsertHolidayDTO insertHolidayDTO,Long id);

}
