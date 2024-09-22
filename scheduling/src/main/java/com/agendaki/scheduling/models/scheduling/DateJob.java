package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_DATE_JOB")
public abstract class DateJob {
    @ManyToOne(fetch = FetchType.LAZY)
    protected Instance instance;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalTime breakInitial;
    protected LocalTime breakFinal;
    protected Boolean isOpen;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DateJob(Instance instance, LocalTime startTime, LocalTime endTime, LocalTime breakInitial, LocalTime breakFinal, Boolean isOpen) {
        this.instance = instance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakInitial = breakInitial;
        this.breakFinal = breakFinal;
        this.isOpen = isOpen;
    }

    public DateJob() {

    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getBreakInitial() {
        return breakInitial;
    }

    public LocalTime getBreakFinal() {
        return breakFinal;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public Instance getInstance() {
        return instance;
    }
}
