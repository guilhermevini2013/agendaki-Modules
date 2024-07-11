package com.agendaki.scheduling.configurations.rabbitmq;

public enum RabbitMQConstants {
    QUEUE_EMAIL_FINANCIALLY("email.financially"),
    QUEUE_SCHEDULING_FINANCIALLY("scheduling.financially"),
    EXCHANGE_NOTIFICATION_AND_SCHEDULING("fanout.notification.scheduling"),;
    private final String value;

    RabbitMQConstants(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
