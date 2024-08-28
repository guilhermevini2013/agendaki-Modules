package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.InputDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INPUT")
public class Input extends Section{
    private String label;
    private Integer width;
    private String placeholder;
    private Boolean isIdentifier;

    public Input(SectionToSaveDTO sectionToSaveDTO) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment());
        if (sectionToSaveDTO instanceof InputDTO){
            InputDTO inputDTO = (InputDTO) sectionToSaveDTO;
            this.label = inputDTO.getLabel();
            this.width = inputDTO.getWidth();
            this.placeholder = inputDTO.getPlaceholder();
            this.isIdentifier = inputDTO.getIdentifier();
        }
    }

    public Input() {

    }
}
