package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import com.agendaki.scheduling.dtos.request.TimeDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TIME_SECTION")
public class TimeSection extends Section{

    public TimeSection(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(),template);
    }
    public TimeSection() {}

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new TimeDTO(this);
    }
}

