package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.Calendar;
import com.agendaki.scheduling.models.template.TypeSection;

public class CalendarDTO extends SectionToSaveDTO{

    public CalendarDTO(TypeSection typeSection, Short position, String horizontalAlignment) {
        super(typeSection, position, horizontalAlignment);
    }

    public CalendarDTO() {
    }

    public CalendarDTO(Calendar calendar) {
        super(TypeSection.CALENDAR, calendar.getPosition(), calendar.getHorizontalAlignment());
    }
}