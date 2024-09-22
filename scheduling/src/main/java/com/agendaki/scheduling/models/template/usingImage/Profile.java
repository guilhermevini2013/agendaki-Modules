package com.agendaki.scheduling.models.template.usingImage;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.dtos.request.template.usingImage.ProfileDTO;
import com.agendaki.scheduling.models.template.Template;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFILE")
public class Profile extends ImageToExhibition {

    public Profile(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super((ProfileDTO) sectionToSaveDTO, template);
    }

    public Profile() {

    }
}
