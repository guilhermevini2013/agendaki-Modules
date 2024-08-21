package com.agendaki.scheduling.services.service;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;
import com.agendaki.scheduling.dtos.response.ReadServiceByInstanceDTO;
import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.ServiceRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceSchedulingServiceImpl implements ServiceSchedulingService {
    private final ServiceRepository serviceRepository;

    public ServiceSchedulingServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void insertService(InsertServiceDTO serviceDTO) {
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Service service = new Service(serviceDTO, instance);
        serviceRepository.save(service);
    }

    @Override
    @Transactional
    public void deleteService(Long id) {
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        serviceRepository.removeServiceAndAssociations(id, instance.getId());
    }

    @Override
    public void updateService() {

    }

    @Override
    public List<ReadServiceByInstanceDTO> getServicesByInstance() {
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        List<Service> servicesByInstance = serviceRepository.findByInstance(instance);
        return servicesByInstance.stream().map(service -> new ReadServiceByInstanceDTO(service)).toList();
    }
}
