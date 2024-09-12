package com.agendaki.scheduling.services.professional;

import com.agendaki.scheduling.dtos.request.ProfessionalInsertDTO;
import com.agendaki.scheduling.dtos.response.ProfessionalAndServiceReadDTO;
import com.agendaki.scheduling.dtos.response.ProfessionalReadByServiceDTO;

import java.util.List;
import java.util.Set;


public interface ProfessionalService {

    void insertProfessionalToInstance(ProfessionalInsertDTO professionals);

    void deleteProfessionalFromInstance(Long idProfessional);

    void disassociateProfessionalOfService(Set<Long> idsToDisassociate,Long idProfessional);

    void associateProfessionalToService(Set<Long> idsToAssociate,Long idProfessional);

    List<ProfessionalReadByServiceDTO> getProfessionalsByService(Long idService);

    List<ProfessionalAndServiceReadDTO> getAllProfessionalAndServices();
}
