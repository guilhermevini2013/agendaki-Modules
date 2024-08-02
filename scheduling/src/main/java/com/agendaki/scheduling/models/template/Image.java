package com.agendaki.scheduling.models.template;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@DiscriminatorValue("IMAGE")
public class Image extends Section{
    private Boolean isPortfolio;
    private String keyImage;

    public Image(String description) {
        super();
        this.keyImage = UUID.randomUUID().toString().replace("-", "");
    }

    public Image() {
    }
}
