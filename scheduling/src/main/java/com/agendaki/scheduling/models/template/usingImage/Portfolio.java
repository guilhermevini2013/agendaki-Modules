package com.agendaki.scheduling.models.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.PortfolioDTO;
import com.agendaki.scheduling.models.template.Template;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PORTFOLIO")
public class Portfolio extends ImageToExhibition {

    public Portfolio(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super((PortfolioDTO) sectionToSaveDTO, template);
    }

    public Portfolio() {

    }

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new PortfolioDTO(this);
    }
}
