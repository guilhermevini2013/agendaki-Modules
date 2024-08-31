package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import com.agendaki.scheduling.dtos.request.ServiceSectionDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SERVICE_SECTION")
public class ServiceSection extends Section{

    public ServiceSection(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(),template);
    }

    public ServiceSection() {}

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new ServiceSectionDTO(this);
    }
}
