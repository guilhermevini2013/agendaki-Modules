package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;
import com.agendaki.scheduling.dtos.response.ReadServiceByInstanceDTO;
import com.agendaki.scheduling.services.service.ServiceSchedulingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/service")
public class ServiceController {
    private final ServiceSchedulingService serviceSchedulingService;

    public ServiceController(ServiceSchedulingService serviceSchedulingService) {
        this.serviceSchedulingService = serviceSchedulingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertServiceToInstance(@RequestBody @Valid InsertServiceDTO insertServiceDTO) {
        serviceSchedulingService.insertService(insertServiceDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@RequestParam(name = "id") Long id) {
        serviceSchedulingService.deleteService(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReadServiceByInstanceDTO> getServicesByInstance() {
        return serviceSchedulingService.getServicesByInstance();
    }

}
