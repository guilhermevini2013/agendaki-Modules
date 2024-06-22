package com.agendaki.financially.dtos.api.dtos.webhook;

import com.agendaki.financially.models.payment.PaymentStatus;

public record ChargesNotificationDTO(String reference_id, PaymentStatus status, String paid_at) {
}
