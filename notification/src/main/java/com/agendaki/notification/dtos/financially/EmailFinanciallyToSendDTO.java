package com.agendaki.notification.dtos.financially;

import com.agendaki.notification.models.TypeTemplate;

import java.util.Map;

public record EmailFinanciallyToSendDTO(Map<String, Object> attributes, String emailTo,
                                        TypeTemplate typeTemplate) {
}
