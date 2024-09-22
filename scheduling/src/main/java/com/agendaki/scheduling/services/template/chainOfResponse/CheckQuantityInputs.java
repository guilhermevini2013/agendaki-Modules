package com.agendaki.scheduling.services.template.chainOfResponse;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckQuantityInputs implements ICheckTemplateToSave {
    @Override
    public void checkSectionsToSave(List<SectionToSaveDTO> sections) {
//        Short quantityInput = 0;
//        for (SectionToSaveDTO section : sections) {
//            if (quantityInput <=4){
//                if (section.getTypeSection() == TypeSection.INPUT) {
//                    quantityInput++;
//                }
//            }else{
//                throw new CheckTemplateException("Quantity is max 4");
//            }
//        }
    }
}
