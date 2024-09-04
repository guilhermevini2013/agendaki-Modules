package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertServiceDTO;
import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Short durationInMinutes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Instance instance;
    @ManyToMany(mappedBy = "services")
    private Set<Professional> professionals = new HashSet<>();

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

    public BigDecimal getPrice() {
        return price;
    }

    public Short getDurationInMinutes() {
        return durationInMinutes;
    }

    public Instance getInstance() {
        return instance;
    }

    public Set<Professional> getProfessionals() {
        return professionals;
    }
}
