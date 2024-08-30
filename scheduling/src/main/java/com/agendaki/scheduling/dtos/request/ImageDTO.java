package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;

public class ImageDTO extends SectionToSaveDTO{
    private boolean isPortfolio;
    private String bio;
    private String imageToBase64;

    public ImageDTO(TypeSection typeSection, Short position, String horizontalAlignment, boolean isPortfolio, String bio, String imageToBase64) {
        super(typeSection, position, horizontalAlignment);
        this.isPortfolio = isPortfolio;
        this.bio = bio;
        this.imageToBase64 = imageToBase64;
    }

    public boolean isPortfolio() {
        return isPortfolio;
    }

    public String getImageToBase64() {
        return imageToBase64;
    }

    public String getBio() {
        return bio;
    }
}
