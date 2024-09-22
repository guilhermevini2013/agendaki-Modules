package com.agendaki.scheduling.dtos.request.template;

import com.agendaki.scheduling.models.template.Help;
import com.agendaki.scheduling.models.template.Section;
import com.agendaki.scheduling.models.template.Template;

import java.util.Arrays;
import java.util.List;

public class HelpDTO extends SectionToSaveDTO {
    private String title;
    private List<String> content;
    private String fontSizeTitle;
    private String fontSizeContent;

    public HelpDTO(Long id, Short position, String horizontalAlignment, String title, List<String> content, String fontSizeTitle, String fontSizeContent) {
        super(id, position, horizontalAlignment);
        this.title = title;
        this.content = content;
        this.fontSizeTitle = fontSizeTitle;
        this.fontSizeContent = fontSizeContent;
    }

    public HelpDTO() {
    }

    public HelpDTO(Help help) {
        super(help.getId(), help.getPosition(), help.getHorizontalAlignment());
        this.title = help.getTitle();
        this.content = Arrays.stream(help.getContent().split("&")).toList();
        this.fontSizeTitle = help.getFontSizeTitle();
        this.fontSizeContent = help.getFontSizeContent();
    }

    @Override
    public Section getClassForDTO(Template template) {
        return new Help(this, template);
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
