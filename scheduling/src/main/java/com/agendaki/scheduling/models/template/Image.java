package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.ImageDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import com.agendaki.scheduling.services.template.ImageService;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@DiscriminatorValue("IMAGE")
public class Image extends Section{
    private Boolean isPortfolio;
    private String bio;
    @Column(columnDefinition = "TEXT")
    private String uuid;

    public Image(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(),template);
        if (sectionToSaveDTO instanceof ImageDTO) {
            ImageDTO imageDTO = (ImageDTO) sectionToSaveDTO;
            this.isPortfolio = imageDTO.isPortfolio();
            if (!imageDTO.isPortfolio()) {
                this.bio = imageDTO.getBio();
            }
            final ImageService imageService = new ImageService();
            String uuidCreated = UUID.randomUUID().toString().replace("-", "");
            imageService.saveImageIntoArchive(imageDTO.getImageToBase64(), uuidCreated);
            this.uuid = uuidCreated;
        }
    }

    public Image() {
    }

    public Boolean getPortfolio() {
        return isPortfolio;
    }

    public String getBio() {
        return bio;
    }

    public String getUuid() {
        return uuid;
    }
}
