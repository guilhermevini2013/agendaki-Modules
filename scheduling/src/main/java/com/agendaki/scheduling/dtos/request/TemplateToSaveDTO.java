package com.agendaki.scheduling.dtos.request;

import java.util.List;

public record TemplateToSaveDTO(String backgroundColor, String primaryColor, String secondaryColor,
                                String tertiaryColor, String fontStyle, List<SectionToSaveDTO> sections) {
}
