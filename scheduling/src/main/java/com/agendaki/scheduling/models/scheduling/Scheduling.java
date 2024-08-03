package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Instance instance;
    private BigDecimal totalPrice;
    private String fullName;
    private LocalDate date;
    private LocalTime startHour;
    @ManyToMany
    private Set<Service> services = new HashSet<>();
    @OneToMany
    private List<ResponseForm> responseForms = new ArrayList<>();


    public Scheduling(Instance instance, BigDecimal totalPrice, String fullName, LocalDate date, LocalTime startHour) {
        this.instance = instance;
        this.totalPrice = totalPrice;
        this.fullName = fullName;
        this.date = date;
        this.startHour = startHour;
    }

    public Scheduling() {
    }
}
