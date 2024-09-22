package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.template.CalendarDTO;
import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CALENDAR")
public class Calendar extends Section {

    public Calendar(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(), template);
        if (sectionToSaveDTO.getId() != null) {
            this.id = sectionToSaveDTO.getId();
        }
    }

    public Calendar() {

    }

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new CalendarDTO(this);
    }
}
