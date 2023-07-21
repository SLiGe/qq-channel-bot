package cn.zjiali.bot.model.message;

/**
 * 表情表态对象
 * @author zJiaLi
 * @since 2023-07-21 15:31
 */
public class MessageReaction {
    /**
     * user_id	string	用户ID
     */
    private String userId;
    /**
     * guild_id	string	频道ID
     */
    private String guildId;
    /**
     * channel_id	string	子频道ID
     */
    private String channelId;
    /**
     * target	ReactionTarget	表态对象
     */
    private ReactionTarget target;
    /**
     * emoji	Emoji	表态所用表情
     */
    private Emoji emoji;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public ReactionTarget getTarget() {
        return target;
    }

    public void setTarget(ReactionTarget target) {
        this.target = target;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }
}
