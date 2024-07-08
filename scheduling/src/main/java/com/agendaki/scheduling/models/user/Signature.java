package com.agendaki.scheduling.models.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeSignature typeSignature;
    private LocalDate expiryDate;

    public Signature(TypeSignature typeSignature, LocalDate expiryDate) {
        this.typeSignature = typeSignature;
        this.expiryDate = expiryDate;
    }

    public Signature() {
    }
}
