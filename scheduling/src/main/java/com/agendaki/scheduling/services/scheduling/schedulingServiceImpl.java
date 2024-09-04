package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.exceptions.ResourceNotFoundException;
import com.agendaki.scheduling.models.scheduling.Scheduling;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.InstanceRepository;
import com.agendaki.scheduling.repositories.SchedulingRepository;
import com.agendaki.scheduling.repositories.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class schedulingServiceImpl implements SchedulingService {
    private final SchedulingRepository schedulingRepository;
    private final ServiceRepository serviceRepository;
    private final InstanceRepository instanceRepository;

    public schedulingServiceImpl(SchedulingRepository schedulingRepository, ServiceRepository serviceRepository, InstanceRepository instanceRepository) {
        this.schedulingRepository = schedulingRepository;
        this.serviceRepository = serviceRepository;
        this.instanceRepository = instanceRepository;
    }

    @Override
    @Transactional
    public void insertScheduling(InsertSchedulingDTO insertSchedulingDTO) {
        Instance instance = instanceRepository.findByKeyInstance(insertSchedulingDTO.idInstance()).orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        var serviceFind = serviceRepository.findById(insertSchedulingDTO.idService()).orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        Scheduling scheduling = new Scheduling(insertSchedulingDTO, instance, serviceFind);
        schedulingRepository.save(scheduling);
    }
}
