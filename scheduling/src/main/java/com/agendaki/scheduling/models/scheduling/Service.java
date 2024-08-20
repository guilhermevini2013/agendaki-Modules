package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;
import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Short durationInMinutes;
    @ManyToOne
    private Instance instance;

    public Service(InsertServiceDTO serviceDTO, Instance instance) {
        this.name = serviceDTO.name();
        this.price = serviceDTO.price();
        this.durationInMinutes = serviceDTO.duration();
        this.instance = instance;
    }

    protected Service() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
