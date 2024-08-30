package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.TemplateToSaveDTO;
import com.agendaki.scheduling.models.user.Instance;
import jakarta.persistence.*;
import org.bouncycastle.util.Times;

import java.util.ArrayList;
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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "template", orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    private Instance instance;

    public Template(TemplateToSaveDTO templateToSaveDTO, Instance instance) {
        this.instance = instance;
        this.backgroundColor = templateToSaveDTO.backgroundColor();
        this.primaryColor = templateToSaveDTO.primaryColor();
        this.secondaryColor = templateToSaveDTO.secondaryColor();
        this.tertiaryColor = templateToSaveDTO.tertiaryColor();
        this.fontStyle = templateToSaveDTO.fontStyle();
        this.sections = createSections(templateToSaveDTO);
    }

    public Template() {

    }

    public void update(TemplateToSaveDTO templateToSaveDTO) {
        this.backgroundColor = templateToSaveDTO.backgroundColor();
        this.primaryColor = templateToSaveDTO.primaryColor();
        this.secondaryColor = templateToSaveDTO.secondaryColor();
        this.tertiaryColor = templateToSaveDTO.tertiaryColor();
        this.fontStyle = templateToSaveDTO.fontStyle();
        this.sections.clear();
        this.sections.addAll(createSections(templateToSaveDTO));
    }

    private List<Section> createSections(TemplateToSaveDTO templateToSaveDTO) {
         return templateToSaveDTO.sections().stream().map(sectionToSaveDTO -> {
            switch (sectionToSaveDTO.getTypeSection()) {
                case HELP -> {
                    return new Help(sectionToSaveDTO,this);
                }
                case CALENDAR -> {
                    return new Calendar(sectionToSaveDTO,this);
                }
                case SERVICE -> {
                    return new ServiceSection(sectionToSaveDTO,this);
                }
                case PROFESSIONAL -> {
                    return new ProfessionalSection(sectionToSaveDTO, this);
                }
                case TIME -> {
                    return new TimeSection(sectionToSaveDTO,this);
                }
                case INPUT -> {
                    return new Input(sectionToSaveDTO,this);
                }
                case IMAGE -> {
                    return new Image(sectionToSaveDTO,this);
                }
                default -> {
                    throw new RuntimeException("Invalid section type: " + sectionToSaveDTO.getTypeSection());
                }
            }
        }).toList();
    }
}
