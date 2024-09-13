package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.ProfessionalSectionDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSIONAL_SECTION")
public class ProfessionalSection extends Section{

    public ProfessionalSection(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(),template);
        if (sectionToSaveDTO.getId() != null) {
            super.id = sectionToSaveDTO.getId();
        }
    }

    public ProfessionalSection() {}

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new ProfessionalSectionDTO(this);
    }
}
