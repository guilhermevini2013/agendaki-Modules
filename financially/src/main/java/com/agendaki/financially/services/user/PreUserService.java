package com.agendaki.financially.services.user;

import com.agendaki.financially.dtos.user.request.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.request.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.request.PreUserUpdateDTO;
import com.agendaki.financially.dtos.user.response.PreUserProfileResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserTokenDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.Transactional;

public interface PreUserService {
    @Transactional
    EntityModel<PreUserSaveResponseDTO> save(PreUserSaveDTO userDTO);

    @Transactional(readOnly = true)
    PreUserTokenDTO load(PreUserLoadDTO userLoadDTO);

    @Transactional
    void update(PreUserUpdateDTO userDTO);

    @Transactional(readOnly = true)
    EntityModel<PreUserProfileResponseDTO> getPreUserById();
}
