package com.agendaki.financially.services.user;

import com.agendaki.financially.dtos.user.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.PreUserTokenDTO;
import com.agendaki.financially.dtos.user.PreUserUpdateDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PreUserService {
    @Transactional
    void save(PreUserSaveDTO userDTO);

    @Transactional(readOnly = true)
    PreUserTokenDTO load(PreUserLoadDTO userLoadDTO);

    @Transactional
    void update(PreUserUpdateDTO userDTO);
}
