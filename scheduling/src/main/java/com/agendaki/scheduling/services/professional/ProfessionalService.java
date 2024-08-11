package com.agendaki.scheduling.services.professional;

import com.agendaki.scheduling.dtos.request.ProfessionalInsertDTO;

import java.util.Set;


public interface ProfessionalService {

    void insertProfessionalToInstance(ProfessionalInsertDTO professionals);

    void deleteProfessionalFromInstance(Long idProfessional);
}
