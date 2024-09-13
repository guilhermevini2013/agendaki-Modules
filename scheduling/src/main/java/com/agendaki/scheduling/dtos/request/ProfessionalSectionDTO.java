package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.ProfessionalSection;
import com.agendaki.scheduling.models.template.TypeSection;

public class ProfessionalSectionDTO extends SectionToSaveDTO{

    public ProfessionalSectionDTO(Long id,TypeSection typeSection, Short position, String horizontalAlignment) {
        super(id,typeSection, position, horizontalAlignment);
    }

    public ProfessionalSectionDTO() {
    }

    public ProfessionalSectionDTO(ProfessionalSection professionalSection) {
        super(professionalSection.getId(), TypeSection.PROFESSIONAL,professionalSection.getPosition(),professionalSection.getHorizontalAlignment());

    }
}
