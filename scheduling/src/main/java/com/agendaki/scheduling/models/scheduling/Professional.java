package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.ProfessionalInsertDTO;
import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Service> services;
    @ManyToOne(fetch = FetchType.LAZY)
    private Instance instance;

    protected Professional() {

    }

    public Professional(ProfessionalInsertDTO professionalInsertDTO, List<Service> servicesFind, Instance instance) {
        this.name = professionalInsertDTO.name();
        this.services = servicesFind.stream().collect(Collectors.toSet());
        this.instance = instance;
    }

    public void updateServices(Set<Long> idsToDisassociate) {
        for (Long idServiceToDisassociate : idsToDisassociate) {
            this.services.removeIf(service -> service.getId().equals(idServiceToDisassociate));
        }
    }

    public String getName() {
        return name;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Instance getInstance() {
        return instance;
    }

    public Long getId() {
        return id;
    }
}
