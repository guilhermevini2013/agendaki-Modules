package com.agendaki.scheduling.models.user;

import com.agendaki.scheduling.models.scheduling.Scheduling;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String keyInstance;
    @OneToMany(mappedBy = "instance")
    private Set<Scheduling> schedulings = new HashSet<>();

    public Instance(Long id) {
        this.id = id;
        this.keyInstance = UUID.randomUUID().toString().replace("-", "");
    }

    public Instance() {

    }

    public Set<Scheduling> getSchedulings() {
        return Collections.unmodifiableSet(schedulings);
    }

    public String getKeyInstance() {
        return keyInstance;
    }
}
