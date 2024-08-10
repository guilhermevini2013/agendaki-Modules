package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class Professional {
    @Id
    private Long id;
    private String name;
    @ManyToMany
    private Set<Service> services;
    @OneToOne
    private Instance instance;

    public Professional() {

    }

    public Professional(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
