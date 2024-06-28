package com.agendaki.notification.models;

import org.thymeleaf.context.Context;

public record EmailContent(String email, String subject, String nameResourceFile, Context context) {
}
