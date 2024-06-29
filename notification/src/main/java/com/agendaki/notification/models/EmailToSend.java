package com.agendaki.notification.models;


public abstract class EmailToSend {
    private final String email;
    private final String subject;
    private final String nameResourceFile;

    public EmailToSend(String email, String subject, String nameResourceFile) {
        this.email = email;
        this.subject = subject;
        this.nameResourceFile = nameResourceFile;
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
