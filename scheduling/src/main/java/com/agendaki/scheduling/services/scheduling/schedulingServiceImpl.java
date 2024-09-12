package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.dtos.response.AllSchedulingReadDTO;
import com.agendaki.scheduling.dtos.response.SchedulingInformationColumDTO;
import com.agendaki.scheduling.exceptions.ResourceNotFoundException;
import com.agendaki.scheduling.models.scheduling.Scheduling;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.InputRepository;
import com.agendaki.scheduling.repositories.InstanceRepository;
import com.agendaki.scheduling.repositories.SchedulingRepository;
import com.agendaki.scheduling.repositories.ServiceRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class schedulingServiceImpl implements SchedulingService {
    private final SchedulingRepository schedulingRepository;
    private final ServiceRepository serviceRepository;
    private final InstanceRepository instanceRepository;
    private final InputRepository inputRepository;

    public schedulingServiceImpl(SchedulingRepository schedulingRepository, ServiceRepository serviceRepository, InstanceRepository instanceRepository, InputRepository inputRepository) {
        this.schedulingRepository = schedulingRepository;
        this.serviceRepository = serviceRepository;
        this.instanceRepository = instanceRepository;
        this.inputRepository = inputRepository;
    }

    @Override
    @Transactional
    public void insertScheduling(InsertSchedulingDTO insertSchedulingDTO) {
        Instance instance = instanceRepository.findByKeyInstance(insertSchedulingDTO.idInstance()).orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        var serviceFind = serviceRepository.findById(insertSchedulingDTO.idService()).orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        Scheduling scheduling = new Scheduling(insertSchedulingDTO, instance, serviceFind);
        schedulingRepository.save(scheduling);
    }

    @Override
    public SchedulingInformationColumDTO getAllScheduling() {
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        List<Scheduling> allByInstance = schedulingRepository.findAllByInstance(instance);
        List<AllSchedulingReadDTO> list = allByInstance.stream().map(scheduling -> new AllSchedulingReadDTO(scheduling)).toList();
        return new SchedulingInformationColumDTO(recoverColumns(instance),list);
    }

    private List<String> recoverColumns(Instance instance){
        return inputRepository.findAllLabelsByInstance(instance);
    }
}
