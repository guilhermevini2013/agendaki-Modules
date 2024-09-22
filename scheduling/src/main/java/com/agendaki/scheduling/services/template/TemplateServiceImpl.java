package com.agendaki.scheduling.services.template;

import com.agendaki.scheduling.dtos.request.template.TemplateDTO;
import com.agendaki.scheduling.exceptions.ResourceNotFoundException;
import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.TemplateRepository;
import com.agendaki.scheduling.services.template.chainOfResponse.ICheckTemplateToSave;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;
    private final Set<ICheckTemplateToSave> checkTemplateToSaves;

    public TemplateServiceImpl(TemplateRepository templateRepository, Set<ICheckTemplateToSave> checkTemplateToSaves) {
        this.templateRepository = templateRepository;
        this.checkTemplateToSaves = checkTemplateToSaves;
    }

    @Override
    @Transactional
    public void updateTemplate(TemplateDTO templateDTO) {
        checkTemplateToSaves.forEach(check -> check.checkSectionsToSave(templateDTO.sections()));
        Instance instanceToAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Optional<Template> templateByInstance = templateRepository.findByInstance(instanceToAuth);
        if (templateByInstance.isPresent()) {
            Template templateFind = templateByInstance.get();
            templateFind.update(templateDTO);
            templateRepository.save(templateFind);
        } else {
            Template template = new Template(templateDTO, instanceToAuth);
            templateRepository.save(template);
        }
    }

    @Override
    public TemplateDTO getTemplate() {
        Instance instanceToAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Template templateByInstance = templateRepository.findByInstance(instanceToAuth).orElseThrow(() -> new ResourceNotFoundException("Template for instance not exist"));
        return new TemplateDTO(templateByInstance);
    }

    @Override
    public TemplateDTO getTemplate(String uuid) {
        Template templateByInstance = templateRepository.findByInstanceId(uuid).orElseThrow(() -> new ResourceNotFoundException("Template for instance not exist"));
        return new TemplateDTO(templateByInstance);
    }
}
