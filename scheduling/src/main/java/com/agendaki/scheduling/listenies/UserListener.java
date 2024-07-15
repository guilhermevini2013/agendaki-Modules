package com.agendaki.scheduling.listenies;

import com.agendaki.scheduling.dtos.request.PreUserToSaveDTO;
import com.agendaki.scheduling.services.user.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
    private final UserService userService;

    public UserListener(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "scheduling.financially")
    public void savePreUser(PreUserToSaveDTO preUserToSaveDTO) {
        userService.saveUser(preUserToSaveDTO);
    }
}
