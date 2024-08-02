package com.agendaki.scheduling.models.template;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_SECTION")
public abstract class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short position;
    private String horizontalAlignment;
    @ManyToOne
    private Template template;

    public Section() {
    }
}
