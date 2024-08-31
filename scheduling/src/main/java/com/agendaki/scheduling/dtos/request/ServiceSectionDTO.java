package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.ServiceSection;
import com.agendaki.scheduling.models.template.TypeSection;

public class ServiceSectionDTO extends SectionToSaveDTO{

    public ServiceSectionDTO(TypeSection typeSection, Short position, String horizontalAlignment) {
        super(typeSection, position, horizontalAlignment);
    }

    public ServiceSectionDTO() {
    }

    public ServiceSectionDTO(ServiceSection serviceSection) {
        super(TypeSection.SERVICE, serviceSection.getPosition(), serviceSection.getHorizontalAlignment());
    }
}
