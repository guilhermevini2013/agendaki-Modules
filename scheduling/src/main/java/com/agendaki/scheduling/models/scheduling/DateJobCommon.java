package com.agendaki.scheduling.models.scheduling;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.DayOfWeek;

@Entity
@DiscriminatorValue("COMMON")
public class DateJobCommon extends DateJob {
    private DayOfWeek dayOfWeek;

    public DateJobCommon() {
    }
}
