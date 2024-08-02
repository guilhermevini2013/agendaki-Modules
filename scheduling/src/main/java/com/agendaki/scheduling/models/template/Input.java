package com.agendaki.scheduling.models.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INPUT")
public class Input extends Section{
    private String label;
    private Integer width;
    private String placeholder;

    public Input() {
    }
}
