package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.repositories.SchedulingRepository;
import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchedulingServiceImpl implements SchedulingService {
    private final SchedulingRepository schedulingRepository;

    public SchedulingServiceImpl(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    @Transactional
    public void insertDateOfScheduling(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated = SecurityUtil.getProjectionOfUserEntityAuthenticated();

    }
}
