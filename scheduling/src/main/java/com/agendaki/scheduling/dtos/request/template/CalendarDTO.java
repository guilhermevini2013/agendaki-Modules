package com.agendaki.scheduling.dtos.request.template;

import com.agendaki.scheduling.models.template.Calendar;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;

public class CalendarDTO extends SectionToSaveDTO {

    public CalendarDTO(Long id, Short position, String horizontalAlignment) {
        super(id, position, horizontalAlignment);
    }

    public CalendarDTO() {
    }

    public CalendarDTO(Calendar calendar) {
        super(calendar.getId(), calendar.getPosition(), calendar.getHorizontalAlignment());
    }

    @Override
    public Section getClassForDTO(Template template) {
        return new Calendar(this, template);
    }
}
