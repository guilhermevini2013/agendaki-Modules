package com.agendaki.scheduling.dtos.response;

import com.agendaki.scheduling.models.scheduling.Service;

public record ReadServiceByInstanceDTO(Long id, String name) {
    public ReadServiceByInstanceDTO(Service service) {
        this(service.getId(),service.getName());
    }
}
