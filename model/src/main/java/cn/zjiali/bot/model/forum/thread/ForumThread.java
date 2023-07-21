package cn.zjiali.bot.model.forum.thread;

/**
 * @author zJiaLi
 * @since 2023-07-21 15:50
 */
public class ForumThread {
    /**
     * guild_id	string	频道ID
     */
    private String guildId;
    /**
     * channel_id	string	子频道ID
     */
    private String channelId;
    /**
     * author_id	string	作者ID
     */
    private String authorId;

    private ForumThreadInfo threadInfo;

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

    public ForumThreadInfo getThreadInfo() {
        return threadInfo;
    }

    public void setThreadInfo(ForumThreadInfo threadInfo) {
        this.threadInfo = threadInfo;
    }
}
