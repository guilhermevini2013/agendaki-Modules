package com.agendaki.scheduling.models.scheduling;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("HOLIDAY")
public class DateJobHoliday extends DateJob {
    private Boolean isOpen;
    private LocalDate date;

    public DateJobHoliday() {

    }
}
