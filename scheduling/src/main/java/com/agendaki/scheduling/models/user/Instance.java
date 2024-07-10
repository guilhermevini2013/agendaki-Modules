package com.agendaki.scheduling.models.user;

import com.agendaki.scheduling.models.scheduling.DateJob;
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
    private Set<Scheduling> schedules = new HashSet<>();
    @OneToMany
    private Set<DateJob> dateJobs = new HashSet<>();

    public Instance(Long id) {
        this.id = id;
        this.keyInstance = UUID.randomUUID().toString().replace("-", "");
    }

    public Instance() {

    }

    public Set<Scheduling> getSchedules() {
        return Collections.unmodifiableSet(schedules);
    }

    public String getKeyInstance() {
        return keyInstance;
    }
}
