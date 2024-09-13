package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.Input;
import com.agendaki.scheduling.models.template.TypeSection;

public class InputDTO extends SectionToSaveDTO {
    private String label;
    private Integer width;
    private boolean isIdentifier;
    private String placeholder;

    public InputDTO(Long id,TypeSection typeSection, Short position, String horizontalAlignment,boolean isIdentifier, String label, Integer width, String placeholder) {
        super(id,typeSection, position, horizontalAlignment);
        this.label = label;
        this.width = width;
        this.placeholder = placeholder;
        this.isIdentifier = isIdentifier;
    }

    public InputDTO() {
    }

    public InputDTO(Input section) {
        super(section.getId(), TypeSection.INPUT, section.getPosition(), section.getHorizontalAlignment());
        this.label = section.getLabel();
        this.width = section.getWidth();
        this.isIdentifier = section.getIdentifier();
    }

    public String getLabel() {
        return label;
    }

    public Integer getWidth() {
        return width;
    }

    public boolean isIdentifier() {
        return isIdentifier;
    }

    public String getPlaceholder() {
        return placeholder;
    }
}
