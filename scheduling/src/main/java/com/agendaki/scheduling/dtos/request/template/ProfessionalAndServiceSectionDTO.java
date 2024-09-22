package com.agendaki.scheduling.dtos.request.template;

import com.agendaki.scheduling.models.template.ProfessionalAndServiceSection;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;

public class ProfessionalAndServiceSectionDTO extends SectionToSaveDTO {

    public ProfessionalAndServiceSectionDTO(Long id, Short position, String horizontalAlignment) {
        super(id, position, horizontalAlignment);
    }

    public ProfessionalAndServiceSectionDTO() {
    }

    public ProfessionalAndServiceSectionDTO(ProfessionalAndServiceSection professionalSection) {
        super(professionalSection.getId(), professionalSection.getPosition(), professionalSection.getHorizontalAlignment());

    }

    @Override
    public Section getClassForDTO(Template template) {
        return new ProfessionalAndServiceSection(this, template);
    }
}
