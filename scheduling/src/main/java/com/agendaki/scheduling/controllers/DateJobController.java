package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;
import com.agendaki.scheduling.services.dateJob.DateJobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/dateJob")
public class DateJobController {
    private final DateJobService dateJobService;

    public DateJobController(DateJobService dateJobService) {
        this.dateJobService = dateJobService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/common")
    public ReadDateOfSchedulingDTO insertDateJob(@RequestBody @Valid InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        ReadDateOfSchedulingDTO readDatesOfSchedulingDTO = dateJobService.insertDateOfScheduling(insertDateOfSchedulingDTO);
        return readDatesOfSchedulingDTO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/holiday")
    public ReadHolidayDTO insertHoliday(@RequestBody @Valid InsertHolidayDTO insertHolidayDTO) {
        ReadHolidayDTO readHolidayDTO = dateJobService.insertHoliday(insertHolidayDTO);
        return readHolidayDTO;
    }

    @GetMapping(value = "/common")
    public ResponseEntity<List<ReadDateOfSchedulingDTO>> getDatesOfScheduling() {
        List<ReadDateOfSchedulingDTO> allDateOfScheduling = dateJobService.getAllDateOfScheduling();
        if (allDateOfScheduling.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allDateOfScheduling);
    }

    @GetMapping(value = "/holiday")
    public ResponseEntity<Set<ReadHolidayDTO>> getHoliday() {
        Set<ReadHolidayDTO> allHoliday = dateJobService.getAllHoliday();
        if (allHoliday.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allHoliday);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDateJob(@RequestParam(name = "id") Long id) {
        dateJobService.deleteDateJobById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/holiday")
    public ReadHolidayDTO updateHoliday(@RequestBody @Valid InsertHolidayDTO insertHolidayDTO, @RequestParam(name = "id") Long id) {
        ReadHolidayDTO readHolidayDTO = dateJobService.updateHolidayById(insertHolidayDTO, id);
        return readHolidayDTO;
    }
}
