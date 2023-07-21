package cn.zjiali.bot.model.forum.reply;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForumReply {
    /**
     * guild_id	string	频道ID
     */
    @JsonProperty("guild_id")
    private String guildId;

    /**
     * channel_id	string	子频道ID
     */
    @JsonProperty("channel_id")
    private String channelId;

    /**
     * author_id	string	作者ID
     */
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("reply_info")
    private ForumReplyInfo replyInfo;

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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public ForumReplyInfo getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(ForumReplyInfo replyInfo) {
        this.replyInfo = replyInfo;
    }
}