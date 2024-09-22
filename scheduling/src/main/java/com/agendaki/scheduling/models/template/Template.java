package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.template.TemplateDTO;
import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String primaryColor;
    private String secondaryColor;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "template", orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    private Instance instance;

    public Template(TemplateDTO templateDTO, Instance instance) {
        this.instance = instance;
        this.primaryColor = templateDTO.primaryColor();
        this.secondaryColor = templateDTO.secondaryColor();
        this.sections = createSections(templateDTO);
    }

    public Template() {}

    public void update(TemplateDTO templateDTO) {
        this.primaryColor = templateDTO.primaryColor();
        this.secondaryColor = templateDTO.secondaryColor();
        this.sections.clear();
        this.sections.addAll(createSections(templateDTO));
    }

    private List<Section> createSections(TemplateDTO templateDTO) {
        return templateDTO.sections().stream().map(sectionToSaveDTO -> sectionToSaveDTO.getClassForDTO(this)).toList();
    }

    public Long getId() {
        return id;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public List<Section> getSections() {
        return Collections.unmodifiableList(this.sections);
    }

    public Instance getInstance() {
        return instance;
    }
}
