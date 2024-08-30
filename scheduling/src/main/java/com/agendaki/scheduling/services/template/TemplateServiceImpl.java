package com.agendaki.scheduling.services.template;

import com.agendaki.scheduling.dtos.request.TemplateToSaveDTO;
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

    /**
     * Esse metodo tem que salvar ou dar Update quando necessario( se caso nao existir template para a Instancia )
     * ele tem que checar se nao existe posicoes sobrepostas. FEITO
     * ele tem que checar se existem mais de 4 inputs. FEITO
     * ele tem que checar se existem, calendario, escolher servico, profissional e horas. FEITO
     * deve conter apenas 1 input indentificador
     * @param templateToSaveDTO
     */
    @Override
    @Transactional
    public void updateTemplate(TemplateToSaveDTO templateToSaveDTO) {
        checkTemplateToSaves.forEach(check -> check.checkSectionsToSave(templateToSaveDTO.sections()));
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
