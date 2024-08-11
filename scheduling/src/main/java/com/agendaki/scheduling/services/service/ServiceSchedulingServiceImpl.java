package com.agendaki.scheduling.services.service;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;
import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.ServiceRepository;
import com.agendaki.scheduling.utils.SecurityUtil;

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
    public void deleteService() {

    }

    @Override
    public void updateService() {

    }
}
