package com.agendaki.scheduling.services.professional;

import com.agendaki.scheduling.dtos.request.ProfessionalInsertDTO;
import com.agendaki.scheduling.exceptions.ResourceNotFoundException;
import com.agendaki.scheduling.models.scheduling.Professional;
import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.ProfessionalRepository;
import com.agendaki.scheduling.repositories.ServiceRepository;
import com.agendaki.scheduling.utils.SecurityUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class ProfessionalServiceImpl implements ProfessionalService {
    private final ProfessionalRepository professionalRepository;
    private final ServiceRepository serviceRepository;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository, ServiceRepository serviceRepository) {
        this.professionalRepository = professionalRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional
    public void insertProfessionalToInstance(ProfessionalInsertDTO professional) {
        Instance instanceToAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Professional professionalCreated = createProfessional(professional, instanceToAuth);
        professionalRepository.save(professionalCreated);
    }

    private Professional createProfessional(ProfessionalInsertDTO professionalInsertDTO, Instance instance) {
        List<Service> servicesFind = serviceRepository.findAllById(professionalInsertDTO.servicesToJobIDs());
        verifyAllServiceExists(servicesFind,professionalInsertDTO.servicesToJobIDs());
        Professional professional = new Professional(professionalInsertDTO, servicesFind, instance);
        return professional;
    }

    private void verifyAllServiceExists(List<Service> servicesFind, Set<Long> servicesToAdd) {
        List<Long> idsOfServicesFind = servicesFind.stream().map(service -> service.getId()).toList();
        for (Long idServiceToAdd : servicesToAdd) {
            if (!idsOfServicesFind.contains(idServiceToAdd)) {
                throw new ResourceNotFoundException("Service for id: " + idServiceToAdd + " not found");
            }
        }
    }

    @Override
    @Transactional
    public void deleteProfessionalFromInstance(Long idProfessional) {
        Instance instanceAuth = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        professionalRepository.deleteByIdAndInstance(idProfessional, instanceAuth);
    }

    @Override
    @Transactional
    public void disassociateProfessionalOfService(Set<Long> idsToDisassociate, Long idProfessional) {
        List<Service> servicesFind = serviceRepository.findAllById(idsToDisassociate);
        verifyAllServiceExists(servicesFind, idsToDisassociate);
        Instance instance = SecurityUtil.getProjectionOfUserEntityAuthenticated().getInstance();
        Professional professional = professionalRepository.findByIdAndInstance(idProfessional, instance)
                .orElseThrow(() -> new ResourceNotFoundException("Professional not found"));
        professional.updateServices(idsToDisassociate);
    }

}
