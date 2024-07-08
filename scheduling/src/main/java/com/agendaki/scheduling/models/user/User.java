package com.agendaki.scheduling.models.user;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String tradeName;
    @OneToOne(fetch = FetchType.LAZY)
    private Signature signature;
    @OneToOne(fetch = FetchType.LAZY)
    private Instance instance;

    public User(String id, String name, String password, String tradeName, Signature signature, Instance instance) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tradeName = tradeName;
        this.signature = signature;
        this.instance = instance;
    }

    public User() {
    }
}
