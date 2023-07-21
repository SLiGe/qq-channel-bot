package cn.zjiali.bot.model.forum.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForumPost {
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

    @JsonProperty("post_info")
    private ForumPostInfo postInfo;

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

    public ForumPostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(ForumPostInfo postInfo) {
        this.postInfo = postInfo;
    }
}