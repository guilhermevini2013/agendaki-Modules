package com.agendaki.scheduling.services.template;

import com.agendaki.scheduling.dtos.request.TemplateToSaveDTO;
import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.TemplateRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    public void updateTemplate(TemplateToSaveDTO templateToSaveDTO) {
        Instance instanceToAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Template template = new Template(templateToSaveDTO, instanceToAuth);
        templateRepository.save(template);
    }
}
