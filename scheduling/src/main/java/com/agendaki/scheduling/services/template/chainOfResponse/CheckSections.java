package com.agendaki.scheduling.services.template.chainOfResponse;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckSections implements ICheckTemplateToSave {
    @Override
    public void checkSectionsToSave(List<SectionToSaveDTO> sections) {

    }

//    @Override
//    public void checkSectionsToSave(List<SectionToSaveDTO> sections) {
//        List<TypeSection> requeridSections = List.of(TypeSection.CALENDAR, TypeSection.PROFESSIONAL_AND_SERVICE);
//        Short totalSectionFind = 0;
//        for (SectionToSaveDTO section : sections) {
//            if (requeridSections.contains(section)) {
//                totalSectionFind++;
//            }
//        }
//        if (totalSectionFind < 2 || totalSectionFind > 2) {
//            throw new CheckTemplateException("The template should contain CALENDAR, PROFESSIONAL_AND_SERVICE");
//        }
//    }
}
