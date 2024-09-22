package com.agendaki.scheduling.dtos.request.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.template.usingImage.Presentation;

public class PresentationDTO extends SectionToSaveDTO {
    private final String imageToBase64;
    private final String text;
    private final String paragraph;

    public PresentationDTO(Long id, Short position, String horizontalAlignment, String imageToBase64, String text, String paragraph) {
        super(id, position, horizontalAlignment);
        this.imageToBase64 = imageToBase64;
        this.text = text;
        this.paragraph = paragraph;
    }

    public PresentationDTO(Presentation presentation) {
       super(presentation.getId(), presentation.getPosition(), presentation.getHorizontalAlignment());
         this.imageToBase64 = presentation.getImage().get(0).getUuidImage();
         this.text = presentation.getText();
         this.paragraph = presentation.getParagraph();
    }

    @Override
    public Section getClassForDTO(Template template) {
        return new Presentation(this, template);
    }

    public String getImageToBase64() {
        return imageToBase64;
    }

    public String getText() {
        return text;
    }

    public String getParagraph() {
        return paragraph;
    }
}
