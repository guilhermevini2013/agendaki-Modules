package com.agendaki.scheduling.models.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "client")
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String tradeName;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Signature signature;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
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

    public User(Map<String, Object> attributes) {
        this.id = (String) attributes.get("id");
        this.name = (String) attributes.get("name");
        this.password = (String) attributes.get("password");
        this.tradeName = (String) attributes.get("tradeName");
        List<Integer> dates = (List<Integer>) attributes.get("expiryDate");
        this.signature = new Signature(TypeSignature.valueOf((String) attributes.get("typeSignature")), LocalDate.of(dates.get(0),dates.get(1),dates.get(2)));
        this.instance = new Instance(this);
    }
}
