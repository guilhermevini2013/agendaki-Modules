package com.agendaki.scheduling.services.user;

import com.agendaki.scheduling.dtos.request.PreUserToSaveDTO;
import com.agendaki.scheduling.dtos.request.UserLoadDTO;
import com.agendaki.scheduling.dtos.response.UserTokenDTO;

public interface UserService {

    void saveUser(PreUserToSaveDTO preUserToSaveDTO);

    UserTokenDTO loadUser(UserLoadDTO userLoadDTO);
}
