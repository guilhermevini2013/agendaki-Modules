package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;

public class ServiceSectionDTO extends SectionToSaveDTO{

    public ServiceSectionDTO(TypeSection typeSection, Short position, String horizontalAlignment) {
        super(typeSection, position, horizontalAlignment);
    }

    public ServiceSectionDTO() {
    }
}
