package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class SectionToSaveDTO {
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

    public void setTypeSection(TypeSection typeSection) {
        this.typeSection = typeSection;
    }
}
