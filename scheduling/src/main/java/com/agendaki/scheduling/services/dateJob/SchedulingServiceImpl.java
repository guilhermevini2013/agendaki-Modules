package com.agendaki.scheduling.services.dateJob;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadDatesOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;
import com.agendaki.scheduling.exceptions.DuplicateDataException;
import com.agendaki.scheduling.exceptions.ResourceNotFoundException;
import com.agendaki.scheduling.models.scheduling.DateJob;
import com.agendaki.scheduling.models.scheduling.DateJobCommon;
import com.agendaki.scheduling.models.scheduling.DateJobHoliday;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.DateJobRepository;
import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
        Set<String> dayOfWeekAlreadyEntered = new HashSet<>();
        datesOfSchedulingDTO.forEach(dateOfScheduling -> {
            if (dayOfWeekAlreadyEntered.contains(dateOfScheduling.dayOfWeek().toString())) {
                throw new DuplicateDataException(dateOfScheduling.dayOfWeek() + " in scheduling already exists");
            }
            DateJobCommon dateJobCommon = new DateJobCommon(dateOfScheduling,projectionOfUserEntityAuthenticated);
            dateJobRepository.save(dateJobCommon);
            dayOfWeekAlreadyEntered.add(dateOfScheduling.dayOfWeek().toString());
        });
        return EntityModel.of(new ReadDatesOfSchedulingDTO(datesOfSchedulingDTO));
    }


    @Override
    @Transactional
    public EntityModel<ReadHolidayDTO> insertHoliday(InsertHolidayDTO insertHolidayDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
        if (dateJobRepository.existsByDate(projectionOfUserEntityAuthenticated.getInstance(), insertHolidayDTO.dateOfHoliday())) {
            throw new DuplicateDataException("Holiday in this date already exists");
        }
        DateJobHoliday dateJobHoliday = new DateJobHoliday(insertHolidayDTO, projectionOfUserEntityAuthenticated);
        dateJobRepository.save(dateJobHoliday);
        return EntityModel.of(new ReadHolidayDTO(insertHolidayDTO));
    }

    @Override
    @Transactional
    public EntityModel<ReadDateOfSchedulingDTO> updateDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        DateJobCommon dateJobFind = (DateJobCommon) dateJobRepository.findByInstanceAndDayOfWeek(instance, insertDateOfSchedulingDTO.dayOfWeek())
                .orElseThrow(() -> new ResourceNotFoundException("Date Job in day of week not exist"));
        dateJobFind.update(insertDateOfSchedulingDTO);
        return EntityModel.of(new ReadDateOfSchedulingDTO(dateJobFind));
    }

}
