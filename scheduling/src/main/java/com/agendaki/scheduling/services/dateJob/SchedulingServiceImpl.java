package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDatesOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;
import com.agendaki.scheduling.models.scheduling.DateJobCommon;
import com.agendaki.scheduling.models.scheduling.DateJobHoliday;
import com.agendaki.scheduling.repositories.DateJobRepository;
import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SchedulingServiceImpl implements DateJobService {
    private final DateJobRepository dateJobRepository;

    public SchedulingServiceImpl(DateJobRepository dateJobRepository) {
        this.dateJobRepository = dateJobRepository;
    }

    @Override
    @Transactional
    public EntityModel<ReadDatesOfSchedulingDTO> insertDateOfScheduling(Set<InsertDateOfSchedulingDTO> datesOfSchedulingDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
        datesOfSchedulingDTO.forEach(dateOfScheduling -> {
            DateJobCommon dateJobCommon = new DateJobCommon(dateOfScheduling,projectionOfUserEntityAuthenticated);
            dateJobRepository.save(dateJobCommon);
        });
        return EntityModel.of(new ReadDatesOfSchedulingDTO(datesOfSchedulingDTO));
    }

    @Override
    public EntityModel<ReadHolidayDTO> insertHoliday(InsertHolidayDTO insertHolidayDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
        DateJobHoliday dateJobHoliday = new DateJobHoliday(insertHolidayDTO, projectionOfUserEntityAuthenticated);
        dateJobRepository.save(dateJobHoliday);
        return EntityModel.of(new ReadHolidayDTO(insertHolidayDTO));
    }

}
