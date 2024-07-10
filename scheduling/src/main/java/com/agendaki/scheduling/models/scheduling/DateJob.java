package com.agendaki.scheduling.models.scheduling;

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
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime breakInitial;
    private LocalTime breakFinal;

    public DateJob() {
    }
}
