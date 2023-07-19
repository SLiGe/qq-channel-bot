package cn.zjiali.bot.core.model.message;

/**
 * @author zJiaLi
 * @since 2023-07-19 10:53
 */
public class MessageReference {

    private String messageId;

    private Boolean ignoreGetMessageError;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Boolean getIgnoreGetMessageError() {
        return ignoreGetMessageError;
    }

    public void setIgnoreGetMessageError(Boolean ignoreGetMessageError) {
        this.ignoreGetMessageError = ignoreGetMessageError;
    }
}
