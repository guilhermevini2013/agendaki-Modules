package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.models.scheduling.DateJobHoliday;
import com.agendaki.scheduling.repositories.DateJobRepository;
import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchedulingServiceImpl implements DateJobService {
    private final DateJobRepository dateJobRepository;

    public SchedulingServiceImpl(DateJobRepository dateJobRepository) {
        this.dateJobRepository = dateJobRepository;
    }

    @Override
    @Transactional
    public void insertDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
    }

    @Override
    public void insertHoliday(InsertHolidayDTO insertHolidayDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
        DateJobHoliday dateJobHoliday = new DateJobHoliday(insertHolidayDTO, projectionOfUserEntityAuthenticated);
        dateJobRepository.save(dateJobHoliday);

    }

}
