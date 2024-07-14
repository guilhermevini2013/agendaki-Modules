package com.agendaki.scheduling.listenies;

import com.agendaki.scheduling.dtos.request.PreUserToSaveDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SchedulingListener {

    @RabbitListener(queues = "scheduling.financially")
    public void savePreUser(PreUserToSaveDTO preUserToSaveDTO) {
        System.out.println(preUserToSaveDTO);
    }
}
