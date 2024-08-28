package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;

public class InputDTO extends SectionToSaveDTO {
    private String label;
    private Integer width;
    private String placeholder;
    private Boolean isIdentifier;

    public InputDTO(TypeSection typeSection, Short position, String horizontalAlignment, String label, Integer width, String placeholder, Boolean isIdentifier) {
        super(typeSection, position, horizontalAlignment);
        this.label = label;
        this.width = width;
        this.placeholder = placeholder;
        this.isIdentifier = isIdentifier;
    }

    public InputDTO() {
    }

    public String getLabel() {
        return label;
    }

    public Integer getWidth() {
        return width;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public Boolean getIdentifier() {
        return isIdentifier;
    }
}
