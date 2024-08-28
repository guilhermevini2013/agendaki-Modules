package com.agendaki.scheduling.services.template;


import com.agendaki.scheduling.dtos.request.TemplateToSaveDTO;

public interface TemplateService {

    void updateTemplate(TemplateToSaveDTO templateToSaveDTO);
}
