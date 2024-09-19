package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.ProfessionalAndServiceSection;
import com.agendaki.scheduling.models.template.TypeSection;

public class ProfessionalAndServiceSectionDTO extends SectionToSaveDTO{

    public ProfessionalAndServiceSectionDTO(Long id, TypeSection typeSection, Short position, String horizontalAlignment) {
        super(id,typeSection, position, horizontalAlignment);
    }

    public ProfessionalAndServiceSectionDTO() {
    }

    public ProfessionalAndServiceSectionDTO(ProfessionalAndServiceSection professionalSection) {
        super(professionalSection.getId(), TypeSection.PROFESSIONAL_AND_SERVICE,professionalSection.getPosition(),professionalSection.getHorizontalAlignment());

    }
}
