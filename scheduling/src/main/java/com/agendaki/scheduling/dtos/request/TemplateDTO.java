package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.Template;

import java.util.List;

public record TemplateDTO(String backgroundColor, String primaryColor, String secondaryColor,
                          String tertiaryColor, String fontStyle, List<SectionToSaveDTO> sections) {
    public TemplateDTO(Template templateByInstance) {
        this(templateByInstance.getBackgroundColor(),templateByInstance.getPrimaryColor(),templateByInstance.getSecondaryColor(),templateByInstance.getTertiaryColor(),
                templateByInstance.getFontStyle(),templateByInstance.getSections().stream().map(section -> section.getDtoForClass()).toList());
    }
}
