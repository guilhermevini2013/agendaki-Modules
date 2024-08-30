package com.agendaki.scheduling.dtos.request;

import com.agendaki.scheduling.models.template.TypeSection;

import java.util.List;

public class HelpDTO extends SectionToSaveDTO{
    private String title;
    private List<String> content;
    private String fontSizeTitle;
    private String fontSizeContent;

    public HelpDTO(TypeSection typeSection, Short position, String horizontalAlignment, String title, List<String> content, String fontSizeTitle, String fontSizeContent) {
        super(typeSection, position, horizontalAlignment);
        this.title = title;
        this.content = content;
        this.fontSizeTitle = fontSizeTitle;
        this.fontSizeContent = fontSizeContent;
    }

    public HelpDTO() {
    }

    public String getTitle() {
        return title;
    }

    public List<String> getContent() {
        return content;
    }

    public String getFontSizeTitle() {
        return fontSizeTitle;
    }

    public String getFontSizeContent() {
        return fontSizeContent;
    }
}
