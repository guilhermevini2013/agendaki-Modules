package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.ServiceSection;
import com.agendaki.scheduling.models.template.TypeSection;

public class ServiceSectionDTO extends SectionToSaveDTO{

    public ServiceSectionDTO(Long id,TypeSection typeSection, Short position, String horizontalAlignment) {
        super(id,typeSection, position, horizontalAlignment);
    }

    public ServiceSectionDTO() {
    }

    public ServiceSectionDTO(ServiceSection serviceSection) {
        super(serviceSection.getId(), TypeSection.SERVICE, serviceSection.getPosition(), serviceSection.getHorizontalAlignment());
    }
}
