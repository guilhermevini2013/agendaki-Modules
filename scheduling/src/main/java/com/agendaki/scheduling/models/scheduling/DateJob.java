package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_DATE_JOB")
public abstract class DateJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Instance instance;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalTime breakInitial;
    protected LocalTime breakFinal;

    public DateJob(Instance instance, LocalTime startTime, LocalTime endTime, LocalTime breakInitial, LocalTime breakFinal) {
        this.instance = instance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakInitial = breakInitial;
        this.breakFinal = breakFinal;
    }

    public DateJob() {

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
}
