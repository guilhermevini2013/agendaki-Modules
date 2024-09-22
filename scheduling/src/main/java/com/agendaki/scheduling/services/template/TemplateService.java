package com.agendaki.scheduling.services.template;


import com.agendaki.scheduling.dtos.request.template.TemplateDTO;

public interface TemplateService {

    void updateTemplate(TemplateDTO templateDTO);

    TemplateDTO getTemplate();

    TemplateDTO getTemplate(String uuid);
}
