package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDatesOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadHolidayDTO;
import com.agendaki.scheduling.services.dateJob.DateJobService;
import com.agendaki.scheduling.utils.HateoasUtil;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/dateJob")
public class DateJobController {
    private final DateJobService dateJobService;

    public DateJobController(DateJobService dateJobService) {
        this.dateJobService = dateJobService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/common")
    public EntityModel<ReadDatesOfSchedulingDTO> insertDateJob(@RequestBody @Valid Set<InsertDateOfSchedulingDTO> insertDateOfSchedulingDTO) {
        EntityModel<ReadDatesOfSchedulingDTO> readDatesOfSchedulingDTOEntityModel = dateJobService.insertDateOfScheduling(insertDateOfSchedulingDTO);
        HateoasUtil.insertHateoasIntoDateJob(readDatesOfSchedulingDTOEntityModel);
        return readDatesOfSchedulingDTOEntityModel;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/holiday")
    public EntityModel<ReadHolidayDTO> insertHoliday(@RequestBody @Valid InsertHolidayDTO insertHolidayDTO) {
        EntityModel<ReadHolidayDTO> readHolidayDTOEntityModel = dateJobService.insertHoliday(insertHolidayDTO);
        HateoasUtil.insertHateoasIntoDateJob(readHolidayDTOEntityModel);
        return readHolidayDTOEntityModel;
    }

}
