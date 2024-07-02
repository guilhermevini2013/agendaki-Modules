package com.agendaki.notification.models;


import org.thymeleaf.context.Context;

import java.util.Map;

public class EmailToSend {
    private final String email;
    private final String subject;
    private final String nameResourceFile;
    private final Context context;

    public EmailToSend(String email, String subject, String nameResourceFile, Map<String, Object> attributes) {
        Context contextTemp = new Context();
        contextTemp.setVariables(attributes);
        this.email = email;
        this.subject = subject;
        this.nameResourceFile = nameResourceFile;
        this.context = contextTemp;
    }

    public Context getContext() {
        return context;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getNameResourceFile() {
        return nameResourceFile;
    }
}
