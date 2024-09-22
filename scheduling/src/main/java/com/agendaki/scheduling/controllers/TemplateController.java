package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.template.TemplateDTO;
import com.agendaki.scheduling.services.template.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/template")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(final TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateTemplate(@RequestBody TemplateDTO templateToSave) {
        templateService.updateTemplate(templateToSave);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TemplateDTO getTemplate(@RequestParam(name = "key",required = false) String uuidInstance) {
        return templateService.getTemplate();
    }

    @GetMapping(value = "/free")
    @ResponseStatus(HttpStatus.OK)
    public TemplateDTO getTemplateNoAuth(@RequestParam(name = "key") String uuidInstance) {
        return templateService.getTemplate(uuidInstance);
    }

}
