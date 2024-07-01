package com.agendaki.financially.configurations.rabbitmq;

public enum RabbitMQConstants {
    QUEUE_EMAIL_FINANCIALLY("email.financially"),
    EXCHANGE_EMAIL_FINANCIALLY("email.fanout.exchange"),;
    private final String value;

    RabbitMQConstants(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
