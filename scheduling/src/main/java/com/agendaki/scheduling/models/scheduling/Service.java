package com.agendaki.scheduling.models.scheduling;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.Duration;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Duration duration;

    public Service(String name, BigDecimal price, Duration duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public Service() {

    }
}
