package com.agendaki.scheduling.services.template.chainOfResponse;

import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;

import java.util.List;

public interface ICheckTemplateToSave {
    void checkSectionsToSave(List<SectionToSaveDTO> sections);
}
