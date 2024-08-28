package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.TemplateToSaveDTO;
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
    public void updateTemplate(@RequestBody TemplateToSaveDTO templateToSave) {
        templateService.updateTemplate(templateToSave);
    }
}
