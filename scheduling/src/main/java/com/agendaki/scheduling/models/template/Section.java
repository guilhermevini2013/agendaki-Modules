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
    @ManyToOne(fetch = FetchType.LAZY)
    private Template template;

    public Section(Short position, String horizontalAlignment, Template template) {
        this.position = position;
        this.horizontalAlignment = horizontalAlignment;
        this.template = template;
    }

    public Section() {
    }

    public Long getId() {
        return id;
    }

    public Short getPosition() {
        return position;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public Template getTemplate() {
        return template;
    }
}
