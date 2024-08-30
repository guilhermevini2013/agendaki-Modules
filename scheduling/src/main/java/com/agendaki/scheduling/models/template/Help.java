package com.agendaki.scheduling.models.template;

import com.agendaki.scheduling.dtos.request.HelpDTO;
import com.agendaki.scheduling.dtos.request.SectionToSaveDTO;
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

    public Help(SectionToSaveDTO sectionToSaveDTO, Template template) {
        super(sectionToSaveDTO.getPosition(), sectionToSaveDTO.getHorizontalAlignment(), template);
        if (sectionToSaveDTO instanceof HelpDTO) {
            HelpDTO helpDTO = (HelpDTO) sectionToSaveDTO;
            this.title = helpDTO.getTitle();
            this.content = formatListToContent(helpDTO.getContent());
            this.fontSizeTitle = helpDTO.getFontSizeTitle();
            this.fontSizeContent = helpDTO.getFontSizeContent();
        }
    }

    private String formatListToContent(List<String> contentList) {
        StringBuilder sb = new StringBuilder();
        contentList.forEach(content -> sb.append("&").append(content));
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
