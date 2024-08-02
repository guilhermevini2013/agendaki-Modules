package com.agendaki.scheduling.models.template;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String backgroundColor;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String fontStyle;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "template")
    private List<Section> sections;

    public Template() {
    }
}
