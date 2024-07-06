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

    public User() {
    }
}
