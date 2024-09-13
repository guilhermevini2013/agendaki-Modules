package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TimeSection;
import com.agendaki.scheduling.models.template.TypeSection;

public class TimeDTO extends SectionToSaveDTO{

    public TimeDTO(Long id,TypeSection typeSection, Short position, String horizontalAlignment) {
        super(id,typeSection, position, horizontalAlignment);
    }

    public TimeDTO() {
    }

    public TimeDTO(TimeSection timeSection) {
        super(timeSection.getId(), TypeSection.TIME,timeSection.getPosition(),timeSection.getHorizontalAlignment());
    }
}
