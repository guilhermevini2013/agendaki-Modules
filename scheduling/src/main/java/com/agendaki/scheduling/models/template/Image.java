package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.ImageDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IMAGE")
public class Image extends Section{
    private Boolean isPortfolio;
    private String bio;
    private String imageToBase64;

    public Image(SectionToSaveDTO sectionToSaveDTO) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment());
        if (sectionToSaveDTO instanceof ImageDTO) {
            ImageDTO imageDTO = (ImageDTO) sectionToSaveDTO;
            this.isPortfolio = imageDTO.getIsPortfolio();
            if (!imageDTO.getIsPortfolio()) {
                this.bio = imageDTO.getBio();
            }
            this.imageToBase64 = imageDTO.getImageToBase64();
        }
    }

    public Image() {
    }

}
