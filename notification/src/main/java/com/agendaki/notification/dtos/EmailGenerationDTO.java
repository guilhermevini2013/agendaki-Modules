package com.agendaki.notification.dtos;

import com.agendaki.notification.models.TypeReceiver;

public record EmailGenerationDTO(String email, TypeReceiver typeReceiver) {
}
