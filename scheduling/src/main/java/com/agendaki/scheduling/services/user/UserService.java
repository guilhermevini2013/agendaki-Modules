package com.agendaki.scheduling.services.user;

import com.agendaki.scheduling.dtos.request.PreUserToSaveDTO;

public interface UserService {

    void saveUser(PreUserToSaveDTO preUserToSaveDTO);
}
