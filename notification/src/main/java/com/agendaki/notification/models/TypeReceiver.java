package com.agendaki.notification.models;

public enum TypeReceiver {
    WELCOME;

    public EmailContent getEmailContent(String email) {
        switch (this) {
            case WELCOME:
                return new EmailContent(email, "Bem-vindo a plataforma AgendaKi", "templates/welcomePreUser.html");
            default:
                throw new IllegalArgumentException("Invalid Type receiver");
        }
    }
    }
