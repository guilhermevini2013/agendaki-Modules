package com.agendaki.scheduling.services.template;

import com.agendaki.scheduling.dtos.request.ImageDTO;
import com.agendaki.scheduling.dtos.request.TemplateToSaveDTO;
import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.TemplateRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Override
    @Transactional
    public void updateTemplate(TemplateToSaveDTO templateToSaveDTO) {
        Instance instanceToAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Optional<Template> templateByInstance = templateRepository.findByInstance(instanceToAuth);
        if (templateByInstance.isPresent()){
            Template templateFind = templateByInstance.get();
            templateFind.update(templateToSaveDTO);
        }else {
            Template template = new Template(templateToSaveDTO, instanceToAuth);
            templateRepository.save(template);
        }
    }
}
