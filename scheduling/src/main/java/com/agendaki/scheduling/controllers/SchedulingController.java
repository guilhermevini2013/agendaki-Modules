package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
import com.agendaki.scheduling.dtos.response.SchedulingInformationColumDTO;
import com.agendaki.scheduling.services.scheduling.SchedulingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping(value = "/public")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertScheduling(@RequestBody InsertSchedulingDTO insertSchedulingDTO) {
        schedulingService.insertScheduling(insertSchedulingDTO);
    }

    @GetMapping(value = "/private")
    public ResponseEntity<SchedulingInformationColumDTO> getAllScheduling() {
        return ResponseEntity.ok(schedulingService.getAllScheduling());
    }
}
