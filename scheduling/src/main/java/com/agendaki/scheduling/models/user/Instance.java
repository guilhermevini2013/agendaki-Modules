package com.agendaki.scheduling.models.user;

import com.agendaki.scheduling.models.scheduling.*;
import com.agendaki.scheduling.models.template.Template;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.*;

@Entity
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String keyInstance;
    @OneToMany(mappedBy = "instance")
    private Set<Scheduling> schedules = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<DateJob> dateJobs;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "instance")
    private Template template;
    @OneToMany
    private Set<Service> services = new HashSet<>();
    @OneToMany
    private Set<Professional> professionals = new HashSet<>();

    public Instance() {
        this.dateJobs = new ArrayList<>(Arrays.asList(
                new DateJobCommon(DayOfWeek.MONDAY, this),
                new DateJobCommon(DayOfWeek.TUESDAY, this),
                new DateJobCommon(DayOfWeek.WEDNESDAY, this),
                new DateJobCommon(DayOfWeek.THURSDAY, this),
                new DateJobCommon(DayOfWeek.FRIDAY, this),
                new DateJobCommon(DayOfWeek.SATURDAY, this),
                new DateJobCommon(DayOfWeek.SUNDAY, this)
        ));
    }

    public Instance(User user) {
        this();
        this.user = user;
        this.keyInstance = UUID.randomUUID().toString().replace("-", "");
    }

    public Set<Scheduling> getSchedules() {
        return Collections.unmodifiableSet(schedules);
    }

    public String getKeyInstance() {
        return keyInstance;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

}
