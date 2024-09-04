package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertSchedulingDTO;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Instance instance;
    private BigDecimal totalPrice;
    private LocalDate date;
    private LocalTime startHour;
    @ManyToOne(fetch = FetchType.LAZY)
    private Service service;
    @OneToOne(fetch = FetchType.LAZY)
    private Professional professional;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ResponseForm> responseForms = new ArrayList<>();

    public Scheduling() {}

    public Scheduling(InsertSchedulingDTO insertSchedulingDTO, Instance instance,Service service) {
        this.instance = instance;
        this.totalPrice = service.getPrice();
        this.service = service;
        this.date = insertSchedulingDTO.date();
        this.startHour = insertSchedulingDTO.startHour();
        this.professional = new Professional(insertSchedulingDTO.idProfessional());
        this.responseForms.addAll(insertSchedulingDTO.responsesForms()
                .stream().map(responseFormDTO -> new ResponseForm(responseFormDTO.id(),responseFormDTO.response(),this)).toList());
    }
}
