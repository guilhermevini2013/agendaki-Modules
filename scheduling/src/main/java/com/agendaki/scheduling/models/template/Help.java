package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.template.HelpDTO;
import com.agendaki.scheduling.dtos.request.template.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("HELP")
public class Help extends Section{
    private String title;
    private String content;
    private String fontSizeTitle;
    private String fontSizeContent;

    public Help() {
    }

    @Override
    public SectionToSaveDTO getDtoForClass() {
        return new HelpDTO(this);
    }

    public Help(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(), template);
        if (sectionToSaveDTO instanceof HelpDTO) {
            HelpDTO helpDTO = (HelpDTO) sectionToSaveDTO;
            if (sectionToSaveDTO.getId() != null) {
                this.id = sectionToSaveDTO.getId();
            }
            this.title = helpDTO.getTitle();
            this.content = formatListToContent(helpDTO.getContent());
            this.fontSizeTitle = helpDTO.getFontSizeTitle();
            this.fontSizeContent = helpDTO.getFontSizeContent();
        }
    }

    private String formatListToContent(List<String> contentList) {
        StringBuilder sb = new StringBuilder();
        contentList.forEach(content -> sb.append(content).append("&"));
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFontSizeTitle() {
        return fontSizeTitle;
    }

    public String getFontSizeContent() {
        return fontSizeContent;
    }
}
