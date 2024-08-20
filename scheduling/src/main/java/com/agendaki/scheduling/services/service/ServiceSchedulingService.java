package com.agendaki.scheduling.services.service;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;
import com.agendaki.scheduling.dtos.response.ReadServiceByInstanceDTO;

import java.util.List;

public interface ServiceSchedulingService {

    void insertService(InsertServiceDTO serviceDTO);

    void deleteService();

    void updateService();

    List<ReadServiceByInstanceDTO> getServicesByInstance();
}
