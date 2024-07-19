package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.services.scheduling.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }


    @PostMapping
    public ResponseEntity<Void> insertDateOfScheduling(@RequestBody InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        schedulingService.insertDateOfScheduling(insertDateOfSchedulingDTO);
        return ResponseEntity.noContent().build();
    }
}
