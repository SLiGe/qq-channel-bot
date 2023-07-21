package cn.zjiali.bot.model.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:23
 */
public class MessageAudited {
    /**
     * audit_id	string	消息审核 id
     */
    @JsonProperty("audit_id")
    private String auditId;
    /**
     * message_id	string	消息 id，只有审核通过事件才会有值
     */
    @JsonProperty("message_id")
    private String messageId;
    /**
     * guild_id	string	频道 id
     */
    @JsonProperty("guild_id")
    private String guildId;
    /**
     * channel_id	string	子频道 id
     */
    @JsonProperty("channel_id")
    private String channelId;
    /**
     * audit_time	ISO8601 timestamp	消息审核时间
     */
    @JsonProperty("audit_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime auditTime;
    /**
     * create_time	ISO8601 timestamp	消息创建时间
     */
    @JsonProperty("create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime createTime;
    /**
     * seq_in_channel	string	子频道消息 seq，用于消息间的排序，seq 在同一子频道中按从先到后的顺序递增，不同的子频道之间消息无法排序
     */
    @JsonProperty("seq_in_channel")
    private String seqInChannel;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getSeqInChannel() {
        return seqInChannel;
    }

    public void setSeqInChannel(String seqInChannel) {
        this.seqInChannel = seqInChannel;
    }
}
