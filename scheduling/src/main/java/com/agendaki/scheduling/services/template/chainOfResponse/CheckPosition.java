package com.agendaki.scheduling.services.template.chainOfResponse;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import com.agendaki.scheduling.exceptions.CheckTemplateException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class CheckPosition implements ICheckTemplateToSave {
    @Override
    public void checkSectionsToSave(List<SectionToSaveDTO> sections) {
        Set<Short> positionsChecked = new HashSet<>();
        for (SectionToSaveDTO section : sections) {
            if (positionsChecked.contains(section.getPosition())) {
                throw new CheckTemplateException("Position " + section.getPosition() + " is already used");
            }
            positionsChecked.add(section.getPosition());
        }
    }
}
