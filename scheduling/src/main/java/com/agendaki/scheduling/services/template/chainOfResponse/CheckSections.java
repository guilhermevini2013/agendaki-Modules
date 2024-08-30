package com.agendaki.scheduling.services.template.chainOfResponse;

import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import com.agendaki.scheduling.exceptions.CheckTemplateException;
import com.agendaki.scheduling.models.template.TypeSection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckSections implements ICheckTemplateToSave {

    @Override
    public void checkSectionsToSave(List<SectionToSaveDTO> sections) {
        List<TypeSection> requeridSections = List.of(TypeSection.CALENDAR, TypeSection.PROFESSIONAL, TypeSection.SERVICE, TypeSection.TIME);
        Short totalSectionFind = 0;
        for (SectionToSaveDTO section : sections) {
            if (requeridSections.contains(section.getTypeSection())) {
                totalSectionFind++;
            }
        }
        if (totalSectionFind < 4 || totalSectionFind > 4) {
            throw new CheckTemplateException("The template should contain CALENDAR, SERVICE, PROFESSIONAL and TIME");
        }
    }
}
