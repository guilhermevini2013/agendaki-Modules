package com.agendaki.scheduling.dtos.request.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.template.usingImage.Portfolio;

import java.util.List;

public class PortfolioDTO extends SectionToSaveDTO {
    private String text;
    private List<String> imageToBase64;

    public PortfolioDTO(Long id, Short position, String horizontalAlignment, String text, List<String> imageToBase64) {
        super(id, position, horizontalAlignment);
        this.text = text;
        this.imageToBase64 = imageToBase64;
    }

    public PortfolioDTO() {
    }

    @Override
    public Section getClassForDTO(Template template) {
        return new Portfolio(this, template);
    }

    public String getText() {
        return text;
    }

    public List<String> getImageToBase64() {
        return imageToBase64;
    }
}
