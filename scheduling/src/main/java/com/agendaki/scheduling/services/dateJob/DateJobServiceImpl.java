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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DateJobServiceImpl implements DateJobService {
    private final DateJobRepository dateJobRepository;

    public DateJobServiceImpl(DateJobRepository dateJobRepository) {
        this.dateJobRepository = dateJobRepository;
    }

    @Override
    @Transactional
    public ReadDatesOfSchedulingDTO insertDateOfScheduling(Set<InsertDateOfSchedulingDTO> datesOfSchedulingDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
        Set<String> dayOfWeekAlreadyEntered = new HashSet<>();
        datesOfSchedulingDTO.forEach(dateOfScheduling -> {
            if (dayOfWeekAlreadyEntered.contains(dateOfScheduling.dayOfWeek().toString())) {
                throw new DuplicateDataException(dateOfScheduling.dayOfWeek() + " in scheduling already exists");
            }
            DateJobCommon dateJobCommon = new DateJobCommon(dateOfScheduling, projectionOfUserEntityAuthenticated);
            dateJobRepository.save(dateJobCommon);
            dayOfWeekAlreadyEntered.add(dateOfScheduling.dayOfWeek().toString());
        });
        return new ReadDatesOfSchedulingDTO(datesOfSchedulingDTO);
    }


    @Override
    @Transactional
    public ReadHolidayDTO insertHoliday(InsertHolidayDTO insertHolidayDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();
        if (dateJobRepository.existsByDate(projectionOfUserEntityAuthenticated.getInstance(), insertHolidayDTO.dateOfHoliday())) {
            throw new DuplicateDataException("Holiday in this date already exists");
        }
        DateJobHoliday dateJobHoliday = new DateJobHoliday(insertHolidayDTO, projectionOfUserEntityAuthenticated);
        dateJobRepository.save(dateJobHoliday);
        return new ReadHolidayDTO(dateJobHoliday);
    }

    @Override
    @Transactional
    public ReadDateOfSchedulingDTO updateDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        DateJobCommon dateJobFind = (DateJobCommon) dateJobRepository.findByInstanceAndDayOfWeek(instance, insertDateOfSchedulingDTO.dayOfWeek())
                .orElseThrow(() -> new ResourceNotFoundException("Date Job in day of week to update not exist"));
        dateJobFind.update(insertDateOfSchedulingDTO);
        return new ReadDateOfSchedulingDTO(dateJobFind);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ReadDateOfSchedulingDTO> getAllDateOfScheduling() {
        Instance userAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Set<DateJob> datesJobByInstance = dateJobRepository.findDateJobCommonByInstance(userAuth);
        return datesJobByInstance.stream().map(dateJob -> new ReadDateOfSchedulingDTO((DateJobCommon) dateJob)).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ReadHolidayDTO> getAllHoliday() {
        Instance userAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Set<DateJob> datesHolidayByInstance = dateJobRepository.findDateJobHolidayByInstance(userAuth);
        return datesHolidayByInstance.stream().map(dateHoliday -> new ReadHolidayDTO((DateJobHoliday) dateHoliday)).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteDateJobById(Long id) {
        Instance userAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        dateJobRepository.deleteByIdAndInstance(id, userAuth);
    }

    @Override
    @Transactional
    public ReadHolidayDTO updateHolidayById(InsertHolidayDTO insertHolidayDTO, Long id) {
        Instance userAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        DateJobHoliday dateJobHoliday = dateJobRepository.getDateHolidayByidAndInstance(id, userAuth)
                .orElseThrow(() -> new ResourceNotFoundException("Date Holiday for instance not exist"));
        dateJobHoliday.update(insertHolidayDTO);
        return new ReadHolidayDTO(dateJobHoliday);
    }

}
