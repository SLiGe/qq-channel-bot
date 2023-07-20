package cn.zjiali.bot.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author zJiaLi
 * @since 2023-07-19 10:55
 */
public class MessageArk {

    @JsonProperty("template_id")
    private Integer templateId;

    /**
     * kv值列表
     */
    private List<MessageArkKv> kv;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public List<MessageArkKv> getKv() {
        return kv;
    }

    public void setKv(List<MessageArkKv> kv) {
        this.kv = kv;
    }
}
