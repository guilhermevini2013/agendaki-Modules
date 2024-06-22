package com.agendaki.financially.dtos.api.dtos.webhook;

import java.util.List;

public record PaymentNotificationDTO(List<ChargesNotificationDTO> charges) {
}
