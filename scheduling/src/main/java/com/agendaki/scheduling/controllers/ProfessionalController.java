package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.ProfessionalInsertDTO;
import com.agendaki.scheduling.services.professional.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/professional")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProfessional(@RequestBody @Valid ProfessionalInsertDTO professionals) {
        professionalService.insertProfessionalToInstance(professionals);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessional(@RequestParam(name = "id") Long id) {
        professionalService.deleteProfessionalFromInstance(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void disassociateProfessionalOfService(@RequestBody Set<Long> idsToDisassociate,@RequestParam(name = "id") Long id) {
        professionalService.disassociateProfessionalOfService(idsToDisassociate, id);
    }
}
