package com.agendaki.scheduling.services.template;

import com.agendaki.scheduling.repositories.TemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public void updateTemplate() {

    }
}
