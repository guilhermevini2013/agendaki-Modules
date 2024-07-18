package com.agendaki.scheduling.configurations.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfiguration {

    @Bean
    public FanoutExchange notificationFanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange(RabbitMQConstants.EXCHANGE_NOTIFICATION_AND_SCHEDULING.value())
                .durable(false)
                .build();
    }

    @Bean
    public Queue scheduling() {
        return QueueBuilder
                .durable(RabbitMQConstants.QUEUE_SCHEDULING_FINANCIALLY.value())
                .build();
    }

    @Bean
    public Binding schedulingBinding() {
        return BindingBuilder
                .bind(scheduling())
                .to(notificationFanoutExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jacksonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonMessageConverter);
        return rabbitTemplate;
    }

}
