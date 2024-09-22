package com.agendaki.scheduling.models.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.PresentationDTO;
import com.agendaki.scheduling.models.template.Template;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRESENTATION")
public class Presentation extends ImageToExhibition {
    private String paragraph;

    public Presentation(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super((PresentationDTO) sectionToSaveDTO, template);
        this.paragraph = ((PresentationDTO) sectionToSaveDTO).getParagraph();
    }

    public Presentation() {

    }

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new PresentationDTO(this);
    }

    public String getParagraph() {
        return paragraph;
    }
}
