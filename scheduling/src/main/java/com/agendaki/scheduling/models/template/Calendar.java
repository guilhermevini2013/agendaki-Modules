package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.CalendarDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CALENDAR")
public class Calendar extends Section{
    public Calendar(SectionToSaveDTO sectionToSaveDTO) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment());
    }

    public Calendar() {

    }
}
