package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;

public class ProfessionalSectionDTO extends SectionToSaveDTO{

    public ProfessionalSectionDTO(TypeSection typeSection, Short position, String horizontalAlignment) {
        super(typeSection, position, horizontalAlignment);
    }

    public ProfessionalSectionDTO() {
    }
}
