package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.HelpDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HELP")
public class Help extends Section{
    private String title;
    private String content;
    private String fontSizeTitle;
    private String fontSizeContent;

    public Help() {
    }

    public Help(SectionToSaveDTO sectionToSaveDTO) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment());
        if (sectionToSaveDTO instanceof HelpDTO) {
            HelpDTO helpDTO = (HelpDTO) sectionToSaveDTO;
            this.title = helpDTO.getTitle();
            this.content = helpDTO.getContent();
            this.fontSizeTitle = helpDTO.getFontSizeTitle();
            this.fontSizeContent = helpDTO.getFontSizeContent();
        }
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
