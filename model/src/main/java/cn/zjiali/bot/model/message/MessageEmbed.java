package cn.zjiali.bot.model.message;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 10:49
 */
public class MessageEmbed {
    /**
     * 标题
     */
    private String title;
    /**
     * 消息弹窗内容
     */
    private String prompt;

    /**
     * 缩略图
     */
    private MessageEmbedThumbnail thumbnail;

    /**
     * embed 字段数据
     */
    private List<MessageEmbedField> fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public MessageEmbedThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MessageEmbedThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<MessageEmbedField> getFields() {
        return fields;
    }

    public void setFields(List<MessageEmbedField> fields) {
        this.fields = fields;
    }
}
