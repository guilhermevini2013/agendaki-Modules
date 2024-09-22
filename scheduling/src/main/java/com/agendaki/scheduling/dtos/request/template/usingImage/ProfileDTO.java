package com.agendaki.scheduling.dtos.request.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.template.usingImage.Profile;

public class ProfileDTO extends SectionToSaveDTO {
    private final String imageToBase64;
    private final String text;

    public ProfileDTO(Long id, Short position, String horizontalAlignment, String imageToBase64, String text) {
        super(id, position, horizontalAlignment);
        this.imageToBase64 = imageToBase64;
        this.text = text;
    }

    public ProfileDTO(Profile profile) {
        super(profile.getId(), profile.getPosition(), profile.getHorizontalAlignment());
        this.imageToBase64 = profile.getImage().get(0).getUuidImage();
        this.text = profile.getText();
    }

    @Override
    public Section getClassForDTO(Template template) {
        return new Profile(this, template);
    }

    public String getImageToBase64() {
        return imageToBase64;
    }

    public String getText() {
        return text;
    }
}
