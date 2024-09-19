package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.Template;

import java.util.List;

public record TemplateDTO(String primaryColor, String secondaryColor,List<SectionToSaveDTO> sections) {
    public TemplateDTO(Template templateByInstance) {
        this(templateByInstance.getPrimaryColor(),templateByInstance.getSecondaryColor(),
                templateByInstance.getSections().stream().map(section -> section.getDtoForClass()).toList());
    }
}
