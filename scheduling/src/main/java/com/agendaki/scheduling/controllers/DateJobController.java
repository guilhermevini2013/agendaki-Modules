package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.services.dateJob.DateJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/scheduling")
public class DateJobController {
    private final DateJobService dateJobService;

    public DateJobController(DateJobService dateJobService) {
        this.dateJobService = dateJobService;
    }

    @PostMapping("/common")
    public ResponseEntity<Void> insertDateJob(@RequestBody InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        dateJobService.insertDateOfScheduling(insertDateOfSchedulingDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/holiday")
    public ResponseEntity<Void> insertHoliday(@RequestBody InsertHolidayDTO insertHolidayDTO) {
        dateJobService.insertHoliday(insertHolidayDTO);
        return ResponseEntity.noContent().build();
    }

}
