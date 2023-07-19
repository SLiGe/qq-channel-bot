package cn.zjiali.bot.core.model.message;

import cn.zjiali.bot.core.model.Member;
import cn.zjiali.bot.core.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 消息对象
 *
 * @author zJiaLi
 * @since 2023-07-19 10:36
 */
public class Message {

    /**
     * 消息创建者
     */
    @JsonProperty("author")
    private User author;
    /**
     * 子频道 id
     */
    @JsonProperty("channel_id")
    private String channelId;
    /**
     * 消息内容
     */
    @JsonProperty("content")
    private String content;
    /**
     * 频道 id
     */
    @JsonProperty("guild_id")
    private String guildId;
    /**
     * 消息 id
     */
    @JsonProperty("id")
    private String id;
    /**
     * 消息创建者的member信息
     */
    @JsonProperty("member")
    private Member member;
    /**
     * 消息创建时间
     */
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime timestamp;
    /**
     * 消息编辑时间
     */
    @JsonProperty("edited_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime editedTimestamp;
    /**
     * 是否是@全员消息
     */
    @JsonProperty("mention_everyone")
    private Boolean mentionEveryone;
    /**
     * 用于消息间的排序，seq 在同一子频道中按从先到后的顺序递增，不同的子频道之间消息无法排序。(目前只在消息事件中有值，2022年8月1日 后续废弃)
     */
    @JsonProperty("seq")
    private Integer seq;

    /**
     * ark消息
     */
    private MessageArk ark;

    /**
     * 附件
     */
    private List<MessageAttachment> attachments;

    /**
     * embed
     */
    private MessageEmbed embed;

    /**
     * 消息中@的人
     */
    private List<User> mentions;
    /**
     * 子频道消息 seq，用于消息间的排序，seq 在同一子频道中按从先到后的顺序递增，不同的子频道之间消息无法排序
     */
    @JsonProperty("seq_in_channel")
    private String seqInChannel;

    /**
     * 引用消息对象
     */
    @JsonProperty("message_reference")
    private MessageReference messageReference;

    /**
     * 用于私信场景下识别真实的来源频道id
     */
    @JsonProperty("src_guild_id")
    private String srcGuildId;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getEditedTimestamp() {
        return editedTimestamp;
    }

    public void setEditedTimestamp(LocalDateTime editedTimestamp) {
        this.editedTimestamp = editedTimestamp;
    }

    public Boolean getMentionEveryone() {
        return mentionEveryone;
    }

    public void setMentionEveryone(Boolean mentionEveryone) {
        this.mentionEveryone = mentionEveryone;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public MessageArk getArk() {
        return ark;
    }

    public void setArk(MessageArk ark) {
        this.ark = ark;
    }

    public List<MessageAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MessageAttachment> attachments) {
        this.attachments = attachments;
    }

    public MessageEmbed getEmbed() {
        return embed;
    }

    public void setEmbed(MessageEmbed embed) {
        this.embed = embed;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public String getSeqInChannel() {
        return seqInChannel;
    }

    public void setSeqInChannel(String seqInChannel) {
        this.seqInChannel = seqInChannel;
    }

    public MessageReference getMessageReference() {
        return messageReference;
    }

    public void setMessageReference(MessageReference messageReference) {
        this.messageReference = messageReference;
    }

    public String getSrcGuildId() {
        return srcGuildId;
    }

    public void setSrcGuildId(String srcGuildId) {
        this.srcGuildId = srcGuildId;
    }
}
