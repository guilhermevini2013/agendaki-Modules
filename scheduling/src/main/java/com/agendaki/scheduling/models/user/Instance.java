package com.agendaki.scheduling.models.user;

import jakarta.persistence.*;

@Entity
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String keyInstance;

    public Instance(Long id, String keyInstance) {
        this.id = id;
        this.keyInstance = keyInstance;
    }

    public Instance() {

    }
}
