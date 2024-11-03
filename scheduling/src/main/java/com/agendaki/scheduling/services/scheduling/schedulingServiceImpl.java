package com.agendaki.scheduling.services.scheduling;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.dtos.response.AllSchedulingReadDTO;
import com.agendaki.scheduling.dtos.response.SchedulingInformationColumDTO;
import com.agendaki.scheduling.exceptions.ResourceNotFoundException;
import com.agendaki.scheduling.models.scheduling.DateJob;
import com.agendaki.scheduling.models.scheduling.Scheduling;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.*;
import com.agendaki.scheduling.repositories.projections.SchedulingTime;
import com.agendaki.scheduling.services.scheduling.factory.TimeSchedulingFactory;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class schedulingServiceImpl implements SchedulingService {
    private final SchedulingRepository schedulingRepository;
    private final ServiceRepository serviceRepository;
    private final InstanceRepository instanceRepository;
    private final InputRepository inputRepository;
    private final DateJobRepository dateJobRepository;

    public schedulingServiceImpl(SchedulingRepository schedulingRepository, ServiceRepository serviceRepository, InstanceRepository instanceRepository, InputRepository inputRepository, DateJobRepository dateJobRepository) {
        this.schedulingRepository = schedulingRepository;
        this.serviceRepository = serviceRepository;
        this.instanceRepository = instanceRepository;
        this.inputRepository = inputRepository;
        this.dateJobRepository = dateJobRepository;
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
        return new SchedulingInformationColumDTO(recoverColumns(instance), list);
    }

    @Override
    public List<LocalTime> getTimeFreeByUuidInstance(String uuidInstance, LocalDate date, Long idProfessional, Long idService) {
        com.agendaki.scheduling.models.scheduling.Service service = serviceRepository.findByIdProfessional(idProfessional, idService).orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        DateJob dateJobCommonByKeyInstance = dateJobRepository.findDateJobCommonByKeyInstance(uuidInstance, date.getDayOfWeek()).orElseThrow(() -> new ResourceNotFoundException("DateJob not found"));
        List<SchedulingTime> allTimeFilled = schedulingRepository.findAllTimeByInstanceAndDate(uuidInstance, date, idProfessional);
        return TimeSchedulingFactory.getTimeFree(service, dateJobCommonByKeyInstance, allTimeFilled);
    }

    @Override
    public void deleteSchedulingById(Long id) {
        schedulingRepository.deleteById(id);
    }

    private List<String> recoverColumns(Instance instance) {
        List<String> allLabelsByInstance = inputRepository.findAllLabelsByInstance(instance);
        allLabelsByInstance.add("Data");
        allLabelsByInstance.add("Horário");
        allLabelsByInstance.add("Profissional");
        allLabelsByInstance.add("Serviço");
        return allLabelsByInstance;
    }
}
