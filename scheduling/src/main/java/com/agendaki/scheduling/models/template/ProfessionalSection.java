package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSIONAL_SECTION")
public class ProfessionalSection extends Section{

    public ProfessionalSection(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(),template);
    }

    public ProfessionalSection() {}
}
