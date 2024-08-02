package com.agendaki.scheduling.models.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CALENDAR")
public class Calendar extends Section{
    public Calendar() {
    }
}
