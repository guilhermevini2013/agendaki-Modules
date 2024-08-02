package com.agendaki.scheduling.models.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HELP")
public class Help extends Section{

    public Help() {
    }
}
