package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.template.ProfessionalAndServiceSectionDTO;
import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSIONAL_SECTION")
public class ProfessionalAndServiceSection extends Section{

    public ProfessionalAndServiceSection(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(),template);
        if (sectionToSaveDTO.getId() != null) {
            super.id = sectionToSaveDTO.getId();
        }
    }

    public ProfessionalAndServiceSection() {}

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new ProfessionalAndServiceSectionDTO(this);
    }
}
