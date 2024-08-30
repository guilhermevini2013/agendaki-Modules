package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;

public class TimeDTO extends SectionToSaveDTO{

    public TimeDTO(TypeSection typeSection, Short position, String horizontalAlignment) {
        super(typeSection, position, horizontalAlignment);
    }

    public TimeDTO() {
    }
}
