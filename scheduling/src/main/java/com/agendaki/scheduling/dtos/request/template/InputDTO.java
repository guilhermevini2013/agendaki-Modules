package com.agendaki.scheduling.dtos.request.template;

import com.agendaki.scheduling.models.template.Input;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;

public class InputDTO extends SectionToSaveDTO {
    private String label;
    private String width;
    private boolean isIdentifier;
    private String placeholder;

    public InputDTO(Long id, Short position, String horizontalAlignment, boolean isIdentifier, String label, String width, String placeholder) {
        super(id, position, horizontalAlignment);
        this.label = label;
        this.width = width;
        this.placeholder = placeholder;
        this.isIdentifier = isIdentifier;
    }

    public InputDTO() {
        super();
    }

    public InputDTO(Input section) {
        super(section.getId(), section.getPosition(), section.getHorizontalAlignment());
        this.label = section.getLabel();
        this.width = section.getWidth();
        this.isIdentifier = section.getIdentifier();
    }

    @Override
    public Section getClassForDTO(Template template) {
        return new Input(this, template);
    }

    public String getLabel() {
        return label;
    }

    public String getWidth() {
        return width;
    }

    public boolean isIdentifier() {
        return isIdentifier;
    }

    public String getPlaceholder() {
        return placeholder;
    }
}
