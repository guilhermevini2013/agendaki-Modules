package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputDTO.class, name = "input")
})
public abstract class SectionToSaveDTO {
    private TypeSection typeSection;
    private Short position;
    private String horizontalAlignment;

    public SectionToSaveDTO(TypeSection typeSection, Short position, String horizontalAlignment) {
        this.typeSection = typeSection;
        this.position = position;
        this.horizontalAlignment = horizontalAlignment;
    }

    public SectionToSaveDTO() {
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
