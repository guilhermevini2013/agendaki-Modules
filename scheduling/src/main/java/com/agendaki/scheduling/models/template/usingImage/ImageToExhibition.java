package com.agendaki.scheduling.models.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.PortfolioDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.PresentationDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.ProfileDTO;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("IMAGE_TO_EXHIBITION")
public abstract class ImageToExhibition extends Section {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "imageToExhibition")
    private List<Image> image = new ArrayList<>();
    private String text;

    public ImageToExhibition(ProfileDTO profileDTO, Template template) {
        super(profileDTO.getPosition(), profileDTO.getHorizontalAlignment(), template);
        if (profileDTO.getId() != null) {
            this.id = profileDTO.getId();
        }
        this.text = profileDTO.getText();
        this.image.add(new Image(profileDTO.getImageToBase64(),this));
    }

    public ImageToExhibition(PresentationDTO presentationDTO, Template template) {
        super(presentationDTO.getPosition(), presentationDTO.getHorizontalAlignment(), template);
        if (presentationDTO.getId() != null) {
            this.id = presentationDTO.getId();
        }
        this.text = presentationDTO.getText();
        this.image.add(new Image(presentationDTO.getImageToBase64(),this));
    }

    public ImageToExhibition(PortfolioDTO portfolioDTO, Template template) {
        super(portfolioDTO.getPosition(), portfolioDTO.getHorizontalAlignment(), template);
        if (portfolioDTO.getId() != null) {
            this.id = portfolioDTO.getId();
        }
        this.text = portfolioDTO.getText();
        this.image = portfolioDTO.getImagesToBase64().stream().map(imageInBase64DTO -> new Image(imageInBase64DTO,this)).toList();
    }

    public ImageToExhibition() {

    }

    @Override
    public abstract SectionToSaveDTO getDtoForClass();

    public List<Image> getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
