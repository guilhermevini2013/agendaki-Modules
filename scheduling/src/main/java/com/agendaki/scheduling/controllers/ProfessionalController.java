package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.ProfessionalInsertDTO;
import com.agendaki.scheduling.dtos.response.ProfessionalAndServiceReadDTO;
import com.agendaki.scheduling.dtos.response.ProfessionalReadByServiceDTO;
import com.agendaki.scheduling.services.professional.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping(value = "/disassociate")
    @ResponseStatus(HttpStatus.OK)
    public void disassociateProfessionalOfService(@RequestBody Set<Long> idsToDisassociate, @RequestParam(name = "id") Long id) {
        professionalService.disassociateProfessionalOfService(idsToDisassociate, id);
    }

    @PutMapping(value = "/associate")
    @ResponseStatus(HttpStatus.OK)
    public void associateProfessionalOfService(@RequestBody Set<Long> idsToAssociate, @RequestParam(name = "id") Long id) {
        professionalService.associateProfessionalToService(idsToAssociate, id);
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalReadByServiceDTO>> getProfessionalsByService(@RequestParam(name = "service") Long idService){
        List<ProfessionalReadByServiceDTO> professionalsByService = professionalService.getProfessionalsByService(idService);
        if (professionalsByService.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professionalsByService);
    }

    @GetMapping(value = "/all")
    public List<ProfessionalAndServiceReadDTO> getProfessionalsAndServices(){
        return this.professionalService.getAllProfessionalAndServices();
    }

}
