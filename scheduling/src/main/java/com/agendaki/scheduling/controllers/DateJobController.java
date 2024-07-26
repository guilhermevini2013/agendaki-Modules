package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.dtos.response.ReadDateOfSchedulingDTO;
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
    @PostMapping(value ="/common")
    public EntityModel<ReadDatesOfSchedulingDTO> insertDateJob(@RequestBody @Valid Set<InsertDateOfSchedulingDTO> insertDateOfSchedulingDTOS) {
        EntityModel<ReadDatesOfSchedulingDTO> readDatesOfSchedulingDTOEntityModel = dateJobService.insertDateOfScheduling(insertDateOfSchedulingDTOS);
        HateoasUtil.insertHateoasIntoDateJob(readDatesOfSchedulingDTOEntityModel);
        return readDatesOfSchedulingDTOEntityModel;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value ="/holiday")
    public EntityModel<ReadHolidayDTO> insertHoliday(@RequestBody @Valid InsertHolidayDTO insertHolidayDTO) {
        EntityModel<ReadHolidayDTO> readHolidayDTOEntityModel = dateJobService.insertHoliday(insertHolidayDTO);
        HateoasUtil.insertHateoasIntoDateJob(readHolidayDTOEntityModel);
        return readHolidayDTOEntityModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/common")
    public EntityModel<ReadDateOfSchedulingDTO> updateDateJob(@RequestBody @Valid InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        EntityModel<ReadDateOfSchedulingDTO> readDateOfSchedulingEntityModel = dateJobService.updateDateOfScheduling(insertDateOfSchedulingDTO);
        HateoasUtil.insertHateoasIntoDateJob(readDateOfSchedulingEntityModel);
        return readDateOfSchedulingEntityModel;
    }
}
