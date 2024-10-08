package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.template.InputDTO;
import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.models.scheduling.ResponseForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("INPUT")
public class Input extends Section {
    private String label;
    private String width;
    private String placeholder;
    private Boolean isIdentifier;
    @OneToMany(mappedBy = "input", cascade = CascadeType.ALL)
    private List<ResponseForm> responseForms = new ArrayList<>();

    public Input(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(), template);
        if (sectionToSaveDTO instanceof InputDTO) {
            InputDTO inputDTO = (InputDTO) sectionToSaveDTO;
            if (sectionToSaveDTO.getId() != null) {
                super.id = sectionToSaveDTO.getId();
            }
            this.label = inputDTO.getLabel();
            this.width = inputDTO.getWidth();
            this.placeholder = inputDTO.getPlaceholder();
            this.isIdentifier = inputDTO.isIdentifier();
        }
    }

    public Input(Long idInput) {
        super.id = idInput;
    }

    public Input() {

    }

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new InputDTO(this);
    }

    public List<ResponseForm> getResponseForms() {
        return responseForms;
    }

    public String getLabel() {
        return label;
    }

    public String getWidth() {
        return width;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public Boolean getIdentifier() {
        return isIdentifier;
    }
}
