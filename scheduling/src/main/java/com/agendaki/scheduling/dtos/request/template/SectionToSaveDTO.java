package com.agendaki.scheduling.dtos.request.template;

import com.agendaki.scheduling.dtos.request.template.usingImage.PortfolioDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.PresentationDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.ProfileDTO;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputDTO.class, name = "input"),
        @JsonSubTypes.Type(value = HelpDTO.class, name = "help"),
        @JsonSubTypes.Type(value = CalendarDTO.class, name = "calendar"),
        @JsonSubTypes.Type(value = ProfessionalAndServiceSectionDTO.class, name = "professionalAndService"),
        @JsonSubTypes.Type(value = PortfolioDTO.class, name = "portfolio"),
        @JsonSubTypes.Type(value = PresentationDTO.class, name = "presentation"),
        @JsonSubTypes.Type(value = ProfileDTO.class, name = "profile"),
})
public abstract class SectionToSaveDTO {
    private Long id;
    private Short position;
    private String horizontalAlignment;

    public SectionToSaveDTO(Long id, Short position, String horizontalAlignment) {
        this.id = id;
        this.position = position;
        this.horizontalAlignment = horizontalAlignment;
    }

    public SectionToSaveDTO() {
    }

    public abstract Section getClassForDTO(Template template);

    public Long getId() {
        return id;
    }

    public Short getPosition() {
        return position;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }
}
