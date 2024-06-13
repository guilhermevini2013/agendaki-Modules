package com.agendaki.financially.services.user;

import com.agendaki.financially.dtos.user.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.PreUserTokenDTO;

public interface PreUserService {

    void save(PreUserSaveDTO userDTO);

    PreUserTokenDTO load(PreUserLoadDTO userLoadDTO);
}
