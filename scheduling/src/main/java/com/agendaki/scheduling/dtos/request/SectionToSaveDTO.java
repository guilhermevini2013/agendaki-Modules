package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputDTO.class, name = "input"),
        @JsonSubTypes.Type(value = HelpDTO.class, name = "help"),
        @JsonSubTypes.Type(value = ImageDTO.class, name = "image"),
        @JsonSubTypes.Type(value = CalendarDTO.class, name = "calendar"),
        @JsonSubTypes.Type(value = ProfessionalAndServiceSectionDTO.class, name = "professionalAndService"),
})
public abstract class SectionToSaveDTO {
    private Long id;
    private TypeSection typeSection;
    private Short position; 
    private String horizontalAlignment;

    public SectionToSaveDTO(Long id, TypeSection typeSection, Short position, String horizontalAlignment) {
        this.id = id;
        this.typeSection = typeSection;
        this.position = position;
        this.horizontalAlignment = horizontalAlignment;
    }

    public SectionToSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public Short getPosition() {
        return position;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public TypeSection getTypeSection() {
        return typeSection;
    }
}
