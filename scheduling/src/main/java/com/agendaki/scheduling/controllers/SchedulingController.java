package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.services.scheduling.SchedulingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertScheduling(@RequestBody InsertSchedulingDTO insertSchedulingDTO) {
        schedulingService.insertScheduling(insertSchedulingDTO);
    }
}
