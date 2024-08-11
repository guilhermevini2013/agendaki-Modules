package com.agendaki.scheduling.services.service;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;

public interface ServiceSchedulingService {

    void insertService(InsertServiceDTO serviceDTO);

    void deleteService();

    void updateService();
}
